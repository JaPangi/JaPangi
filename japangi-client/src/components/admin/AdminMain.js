import { Link, useLocation, useNavigate, useParams } from "react-router-dom"
import styled from "styled-components"
import { useState, useEffect } from "react"
import request from "../../request/Request"
import IsAdminLoggedIn from "../../login/IsAdminLoggedIn"

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
    justify-content: space-around;
    align-items: center;
    flex-wrap: wrap;
`

const Button = styled.button`
    width: 43%;
    height: 40%;
    background-color: #ffffff;
    border: 2px solid lightgray;
    border-radius: 15px;
    transition: .2s ease;
    font-size: 24px;
    color: gray;
    font-weight: 500;

    &:hover {
        border: 2px solid #F69B0B;
        transform: translateY(-1%);
    }
`

export default function AdminMain() {

    const navigate = useNavigate();
    const params = useParams()
    const [username, setUsername] = useState("");
    
    IsAdminLoggedIn()

    useEffect(() => {
        const user = window.sessionStorage.getItem("username")
        setUsername(user)
    }, [])

    const HandleAdminButtonClick = (e) => {
        if (window.confirm("Would you like to go to the admin password change page?")) {
            navigate("/admin/password/change/" + username)
        } else {
        }
    }

    const HandleLinkButtonClick = (e) => {
        navigate("/admin/vendingmachine/" + params.vendingmachineId + "/manage/" + e.target.name)
    }

    function handleCollectButton(e) {
        request("ADMIN_COLLECT_MONEY_" + params.vendingmachineId, null)
        .then(res => {
            console.log(res.data)
        })
    }

    return (
        <Wrapper>
            <Title>
                <AdminTitle onClick={HandleAdminButtonClick}>{username}</AdminTitle>
                <ContentTitle>
                    <ContentName>Vending Machine #{params.vendingmachineId}</ContentName>
                    <DeleteButton>remove</DeleteButton>
                </ContentTitle>
            </Title>
            <Content>
                <Button name="drink" onClick={HandleLinkButtonClick}>음료 관리</Button>
                <Button name="statistic" onClick={handleCollectButton}>수금</Button>
                <Button name="change" onClick={HandleLinkButtonClick}>거스름돈 관리</Button>
                <Button name="statistic" onClick={HandleLinkButtonClick}>통계 확인</Button>
            </Content>
        </Wrapper>
    )
}