import { Link, useLocation, useNavigate, useParams } from "react-router-dom"
import styled from "styled-components"
import { useState, useEffect, useRef } from "react"
import request from "../../../request/Request"
import IsAdminLoggedIn from "../../../login/IsAdminLoggedIn"

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

const DeleteButton = styled.button`
    width: 70px;
    height: 30px;
    margin-right: 35px;
    color: gray;
    background-color: #ffffff;
    border: none;
    font-size: 14px;
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
    justify-content: center;
    align-items: center;
    flex-direction: column;
`

const InsertCoinsWrapper = styled.div`
    width: 70%;
    height: 70%;
    display: flex;
    flex-direction : column;
    align-items: center;
`

const InputBox = styled.div`
    width: 90%;
    height: 42px;
    margin-top: 17px;
    display: flex;
    justify-content: space-between;
`

const CoinDetail = styled.div`
    width: 30%;
    height: 100%;
    line-height: 42px;
    padding-left: 30px;
    box-sizing: border-box;
    font-size: 18px;
    font-weight: 500;
    color: #1c1b1a;
`

const CoinInput = styled.div`
    width: 70%;
    height: 100%;
    display: flex;
    justify-content: right;
    padding-right: 30px;
    box-sizing: border-box;
`

const CoinAmountState = styled.div`
    width: 100px;
    height: 40px;
    text-align: center;
    line-height: 40px;
    font-size: 16px;
    font-weight: 500;
    color: #1c1b1a;
`

const CoinInputButton = styled.button`
    width: 40px;
    height: 40px;
    border-radius: 20px;
    background-color: lightgray;
    outline: none;
    border: none;
    font-size: 20px;
    font-weight: 400;
    color: #ffffff;
    transition: .3s ease;

    &:hover {
        background-color: #F69B0B;
    }
` 

const Button = styled.button`
    width: 58%;
    height: 50px;
    border-radius: 12px;
    padding-left: 20px;
    padding-right: 20px;
    box-sizing: border-box;
    outline: none;
    border: none;
    color: #ffffff;
    background-color: #F69B0B; 
    font-size: 16px;
    font-weight: 600;
    transition: .2s ease; 

    &:hover {
        opacity: 70%;
    }
`

export default function ManageChange() {

    const navigate = useNavigate();
    const params = useParams()
    const [username, setUsername] = useState("");
    const [moneyInput, setMoneyInput] = useState({10:0, 50:0, 100:0, 500:0, 1000:0})
    const [initialMoneyState, setInitialMoneyState] = useState({10:0, 50:0, 100:0, 500:0, 1000:0})
    
    IsAdminLoggedIn()

    useEffect(() => {
        const user = window.sessionStorage.getItem("username")
        setUsername(user)

        request("ADMIN_GET_CHANGE_" + params.vendingmachineId, null)
        .then(res => {
            var tmpChanges = {"10" : 0,"50" : 0,"100" : 0,"500" : 0,"1000" : 0}
    
            res.data.data.map(v => {
                tmpChanges[v.value] = v.amount
            })
        
            setMoneyInput(tmpChanges)
            setInitialMoneyState({...tmpChanges})
        })
    }, [])

    const HandleAdminButtonClick = (e) => {
        if (window.confirm("Would you like to go to the admin password change page?")) {
            navigate("/admin/password/change/" + username)
        } else {
        }
    }

    function handleMoneyInputButton(e) {
        e.preventDefault();
        const buttonType = e.target.innerText
        const target = e.target.parentNode.parentNode.firstChild.innerText.split(" ")[1]

        if (buttonType === "+") {
            setMoneyInput((prevState) => {
                return { ...prevState, target: moneyInput[target] += 1 }
            })
        } else if (buttonType === "-") {
            if (moneyInput[target] - 1 < initialMoneyState[target]) {
                alert("거스름돈은 기존보다 작아질 수 없습니다.")
                return
            }
            setMoneyInput((prevState) => {
                return { ...prevState, target: moneyInput[target] -= 1 }
            })
        }
    }

    function HandleApplyButton(e) {
        const data = {

        }
    } 

    return (
        <Wrapper>
            <Title>
                <AdminTitle onClick={HandleAdminButtonClick}>{username}</AdminTitle>
                <ContentTitle>
                    <ContentName>Vending Machine #{params.vendingmachineId} / Changes</ContentName>
                    <DeleteButton></DeleteButton>
                </ContentTitle>
            </Title>
            <Content>
                <InsertCoinsWrapper>
                        <InputBox>
                            <CoinDetail>
                                ￦ 10
                            </CoinDetail>
                            <CoinInput>
                                <CoinInputButton onClick={handleMoneyInputButton}>-</CoinInputButton>
                                <CoinAmountState>{moneyInput[10]}</CoinAmountState>
                                <CoinInputButton onClick={handleMoneyInputButton}>+</CoinInputButton>
                            </CoinInput>
                        </InputBox>

                        <InputBox>
                            <CoinDetail>
                                ￦ 50
                            </CoinDetail>
                            <CoinInput>
                                <CoinInputButton onClick={handleMoneyInputButton}>-</CoinInputButton>
                                <CoinAmountState>{moneyInput[50]}</CoinAmountState>
                                <CoinInputButton onClick={handleMoneyInputButton}>+</CoinInputButton>
                            </CoinInput>
                        </InputBox>

                        <InputBox>
                            <CoinDetail>
                                ￦ 100
                            </CoinDetail>
                            <CoinInput>
                                <CoinInputButton onClick={handleMoneyInputButton}>-</CoinInputButton>
                                <CoinAmountState>{moneyInput[100]}</CoinAmountState>
                                <CoinInputButton onClick={handleMoneyInputButton}>+</CoinInputButton>
                            </CoinInput>
                        </InputBox>

                        <InputBox>
                            <CoinDetail>
                                ￦ 500
                            </CoinDetail>
                            <CoinInput>
                                <CoinInputButton onClick={handleMoneyInputButton}>-</CoinInputButton>
                                <CoinAmountState>{moneyInput[500]}</CoinAmountState>
                                <CoinInputButton onClick={handleMoneyInputButton}>+</CoinInputButton>
                            </CoinInput>
                        </InputBox>

                        <InputBox>
                            <CoinDetail>
                                ￦ 1000
                            </CoinDetail>
                            <CoinInput>
                                <CoinInputButton onClick={handleMoneyInputButton}>-</CoinInputButton>
                                <CoinAmountState>{moneyInput[1000]}</CoinAmountState>
                                <CoinInputButton onClick={handleMoneyInputButton}>+</CoinInputButton>
                            </CoinInput>
                        </InputBox>
                    </InsertCoinsWrapper>
                    <Button>apply</Button>
            </Content>
        </Wrapper>
    )
}