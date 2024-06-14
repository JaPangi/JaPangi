import { Link, useLocation, useNavigate, useParams } from "react-router-dom"
import styled from "styled-components"
import { useState, useEffect } from "react"
import IsAdminLoggedIn from "../../../login/IsAdminLoggedIn"
import request from "../../../request/Request"
import Calendar from "./Calendar"

const Wrapper = styled.div`
    width: 100%;
    height: 100%;
    border-radius: 17px;
    display: flex;
    justify-content: space-between;
    flex-direction: column;
`

const Title = styled.div`
    width: 100%;
    height: 12%;
    display: flex;
    justify-content: space-between;
`

const AdminTitle = styled.button`
    width: 20%;
    height: 87px;
    box-sizing: border-box;
    background-color: #ffffff;
    border-radius: 17px;
    text-align: center;
    line-height: 87px;
    font-size: 20px;
    font-weight: 600;
    box-shadow: 2px 8px 12px rgba(1, 1, 1, 0.02);
    border: none;
`

const ContentTitle = styled.div`
    width: 78%;
    height: 87px;
    padding-left: 35px;
    box-sizing: border-box;
    background-color: #ffffff;
    border-radius: 17px;
    line-height: 82px;
    font-size: 20px;
    font-weight: 500;
    box-shadow: 2px 8px 12px rgba(1, 1, 1, 0.02);
    display: flex;
    justify-content: space-between;
    align-items: center;
`

const ContentName = styled.div`
`

const TypeButton = styled.select`
    width: 70px;
    height: 30px;
    margin-right: 35px;
    color: gray;
    background-color: #ffffff;
    border: none;
    font-size: 14px;
    outline: none;
`

const Content = styled.div`
    width: 100%;
    height: 85%;
    background-color: #ffffff;
    border-radius: 17px;
    box-shadow: 2px 8px 12px rgba(1, 1, 1, 0.02);
    padding: 40px;
    box-sizing: border-box;
    display: flex;
    justify-content: space-between;
    align-items: center;
    flex-direction: column;
`

const MonthTitleWrap = styled.div`
    width: 70%;
    height: 10%;
    display: flex;
    justify-content: space-between;
    align-items: center;
    color: gray;
`

const MonthButton = styled.button`
    font-size: 24px;
    background-color: #ffffff;
    border: none;
    color: lightgray;
    font-weight: 600;
`

const MonthTitle = styled.div`
    font-size: 24px;
`

const StatisticWrap = styled.div`
    width: 70%;
    height: 80%;
    overflow: scroll;
`

const Total = styled.div`
    width: 70%;
    height: 7%;
    color: gray;
    margin-top: 10px;
`

export default function ManageStatictic() {

    const navigate = useNavigate();
    const params = useParams()
    const [username, setUsername] = useState("");
    const [month, setMonth] = useState(0);
    const [year, setYear] = useState(0);
    const [values, setValues] = useState([])
    const [totalAmount, setTotalAmount] = useState(0)
    const [totalPrice, setTotalPrice] = useState(0)
    const [statistic, setStatistic] = useState([])
    const [drinks, setDrinks] = useState([])
    const [type, setType] = useState("all")

    IsAdminLoggedIn()
    
    useEffect(() => {
        const user = window.sessionStorage.getItem("username")
        setUsername(user)

        const now = new Date()
        const _year = now.getFullYear()
        const _month = now.getMonth() + 1
        setYear(_year)
        setMonth(_month)

        request("ADMIN_GET_STATISTICS_" + params.vendingmachineId, null)
        .then(res => {
            setStatistic(prev => res.data.data)
            var target = _year + "-" + _month
            if (month < 10) {
                target = _year + "-0" + _month
            }
            res.data.data.map(e => {
                if (e.month === target) {
                    setTotalAmount(e.totalAmount)
                    setTotalPrice(e.totalPrice)
                    setValues(e.dailyStatisticResponses)
                }
            })

            request("VENDING_MACHINE_INFO_" + params.vendingmachineId, null)
            .then(res => {
                setDrinks(res.data.data)
            })
        })
    }, [])

    const HandleAdminButtonClick = (e) => {
        if (window.confirm("Would you like to go to the admin password change page?")) {
            navigate("/admin/password/change/" + username)
        } else {
        }
    }

    const prevText = "<"
    const nextText = ">"

    const handleMonthChangeButton = (e) => {
        var _year
        var _month
        if (e.target.name === ">") {
            if (month === 12) {
                setMonth(month => 1)
                setYear(year => year + 1)
                _year = year + 1
                _month = 1
            } else {
                setMonth(month => month + 1)
                _year = year
                _month = month + 1
            }
        } else {
            if (month === 1) {
                setMonth(month => 12)
                setYear(year => year - 1)
                _year = year - 1
                _month = 12
            } else {
                setMonth(month => month - 1)
                _year = year
                _month = month - 1
            }
        }

        var target = _year + "-" + _month
        if (_month < 10) {
            target = _year + "-0" + _month
        }

        var isChanged = false
        statistic.map(d => {
            if (d.month === target) {
                isChanged = true
                setTotalAmount(amount => d.totalAmount)
                setTotalPrice(price => d.totalPrice)
                setValues(values => d.dailyStatisticResponses)
            }
        })
        if (!isChanged) {
            setTotalAmount(amount => 0)
            setTotalPrice(price => 0)
            setValues(values => [])
        }
    } 

    function onTypeChange(e) {
        setType(e.target.value)
        var targetDrink
        drinks.map(d => {
            if (d.drinkName === e.target.value) {
                targetDrink = d.drinkId
            }
        })

        if (targetDrink === undefined) {
            request("ADMIN_GET_STATISTICS_" + params.vendingmachineId, null)
                .then(res => {
                    setStatistic(prev => res.data.data)
                    var target = year + "-" + month
                    if (month < 10) {
                        target = year + "-0" + month
                    }
                    res.data.data.map(e => {
                        if (e.month === target) {
                            setTotalAmount(e.totalAmount)
                            setTotalPrice(e.totalPrice)
                            setValues(e.dailyStatisticResponses)
                        }
                    })
                })
        } else {
            const data = {
                drinkId: targetDrink
            }
            request("ADMIN_GET_DRINK_STATISTICS_" + params.vendingmachineId, data)
                .then(res => {
                    setStatistic(prev => res.data.data)
                    var target = year + "-" + month
                    if (month < 10) {
                        target = year + "-0" + month
                    }
                    res.data.data.map(e => {
                        if (e.month === target) {
                            setTotalAmount(e.totalAmount)
                            setTotalPrice(e.totalPrice)
                            setValues(e.dailyStatisticResponses)
                        }
                    })
                })
        }
    }

    return (
        <Wrapper>
            <Title>
                <AdminTitle onClick={HandleAdminButtonClick}>{username}</AdminTitle>
                <ContentTitle>
                    <ContentName>Vending Machine #{params.vendingmachineId} / Statistic</ContentName>
                    <TypeButton onChange={onTypeChange} value={type}>
                        <option id={"all"}>all</option>
                        {
                            drinks.map(d => {
                                return <option value={d.drinkName}>{d.drinkName}</option>
                            })
                        }
                    </TypeButton>
                </ContentTitle>
            </Title>
            <Content>
                <MonthTitleWrap>
                    <MonthButton onClick={handleMonthChangeButton} name="<">{prevText}</MonthButton>
                    <MonthTitle>{year}.{month}</MonthTitle>
                    <MonthButton onClick={handleMonthChangeButton} name=">">{nextText}</MonthButton>
                </MonthTitleWrap>
                <Total>Total Order Amount: {totalAmount}, Total Price: ￦ {totalPrice}</Total>
                <StatisticWrap>
                    <Calendar year={year} month={month} data={values} />
                </StatisticWrap>
            </Content>
        </Wrapper>
    )
}