import { Link, useLocation, useNavigate } from "react-router-dom"
import styled from "styled-components"

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
`

export default function AdminStatistic() {

    const navigate = useNavigate();

    const HandleAdminButtonClick = (e) => {
        if (window.confirm("Would you like to go to the admin password change page?")) {
            navigate("/admin/password/change")
        } else {
        }
    }

    return (
        <Wrapper>
            <Title>
                <AdminTitle onClick={HandleAdminButtonClick}>wwan13</AdminTitle>
                <ContentTitle>
                    <ContentName>Vending Machine #2</ContentName>
                    <DeleteButton>remove</DeleteButton>
                </ContentTitle>
            </Title>
            <Content>
                
            </Content>
        </Wrapper>
    )
}