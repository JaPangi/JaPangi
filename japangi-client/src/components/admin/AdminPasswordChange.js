import { useNavigate } from "react-router-dom"
import styled from "styled-components"

const Wrapper = styled.div`
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
`

const Content = styled.div`
    width: 350px;
    height: 400px;
    background-color: #ffffff;
    border-radius: 17px;
    padding: 25px;
    box-sizing: border-box;
`

const Title = styled.div`
    width: 350px;
    height: 78px;
    line-height: 78px;
    padding-left: 13px;
    font-size: 23px;
    font-weight: 600;
`

const Description = styled.div`
    width: 300px;
    margin-bottom: 7px;
    padding-left: 3px;
    color: gray;
`

const InputBox = styled.input`
    width: 292px;
    height: 30px;
    border: none;
    border: 2px solid lightgray;
    border-radius: 5px;
    margin-bottom: 15px;
    outline: none;
    padding-left: 10px;
    box-sizing: border-box;
`

const Empty = styled.div`
    width: 350px;
    height: 15px;
`

const SubmitButton = styled.button`
    width: 300px;
    height: 45px;
    background-color: #F69B0B;
    border-radius: 8px;
    outline: none;
    border: none;
    color: #ffffff;
    font-size: 20px;
    font-weight: 500;
    transition: .2s ease;  
    margin-top: 55px;

    &:hover {
        opacity: 70%;
    }
`

export default function AdminPasswordChange() {

    const navigate = useNavigate()

    const handleSubmitButton = () => {
        navigate("/admin/login")
    }

    return (
        <Wrapper>
            <Title>Change Password</Title>
            <Content>
                <Description>original password</Description>
                <InputBox type={"password"} />

                <Empty />

                <Description>change password</Description>
                <InputBox type={"password"} />
                <Description>password confirm</Description>
                <InputBox type={"password"} />
                <SubmitButton onClick={handleSubmitButton}>Submit</SubmitButton>
            </Content>
        </Wrapper>
    )
}