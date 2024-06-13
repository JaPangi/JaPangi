import { Link, useLocation, useNavigate, useParams } from "react-router-dom"
import styled from "styled-components"
import { useState, useEffect } from "react"
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
    flex-wrap: wrap;
    overflow: scroll;
`

const Element = styled.button`
    width: 100%;
    height: 80px;
    border: 2px solid lightgray;
    border-radius: 12px;
    background-color: #ffffff;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding-left: 50px;
    padding-right: 50px;
    margin-bottom: 25px;
    transition: .2s ease;

    &:hover{
        border: 2px solid #F69B0B;
        transform: translateY(-3%);
    }
`

const InfoElement = styled.div`
    color: gray;
    font-weight: 500;
    font-size: 17px;
    width: 120px;
`

export default function ManageDrink() {

    const navigate = useNavigate();
    const params = useParams()
    const [username, setUsername] = useState("")
    const [drinks, setDrinks] = useState([])

    IsAdminLoggedIn()
    
    useEffect(() => {
        const user = window.sessionStorage.getItem("username")
        setUsername(user)

        request("VENDING_MACHINE_INFO_" + params.vendingmachineId, null)
        .then(res => {
            if (res.data.staus === "ERROR") {
                alert(res.data.message)
            }
            setDrinks(res.data.data)
        })
    }, [])

    const HandleAdminButtonClick = (e) => {
        if (window.confirm("Would you like to go to the admin password change page?")) {
            navigate("/admin/password/change/" + username)
        } else {
        }
    }

    const HandleDrinkButtonClick = (e) => {
        navigate("/admin/vendingmachine/" + params.vendingmachineId + "/manage/drink/" + e.target.id)
    }

    return (
        <Wrapper>
            <Title>
                <AdminTitle onClick={HandleAdminButtonClick}>{username}</AdminTitle>
                <ContentTitle>
                    <ContentName>Vending Machine #{params.vendingmachineId} / Drinks</ContentName>
                    <DeleteButton></DeleteButton>
                </ContentTitle>
            </Title>
            <Content>
                {
                    drinks &&
                    drinks.map(d => {
                        return (
                            <Element onClick={HandleDrinkButtonClick} id={d.drinkId}>
                                <InfoElement id={d.drinkId}>{d.drinkName}</InfoElement>
                                <InfoElement id={d.drinkId}>￦ {d.drinkPrice}</InfoElement>
                                <InfoElement id={d.drinkId}>{d.stock} left</InfoElement>
                            </Element>                                
                        )
                    })
                }
            </Content>
        </Wrapper>
    )
}