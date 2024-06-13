import { useEffect, useState } from "react"
import { useNavigate, useParams } from "react-router-dom"
import styled from "styled-components"
import request from "../../request/Request"

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
    height: 38px;
    border: none;
    border: 2px solid lightgray;
    border-radius: 8px;
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
    margin-top: 45px;

    &:hover {
        opacity: 70%;
    }
`

export default function AdminPasswordChange() {

    const navigate = useNavigate()
    const params = useParams()
    const [values, setValues] = useState({
        original: "",
        change: "",
        confirm: "",
    })

    useEffect(() => {
        document.cookie = "user=unknown;"
    })

    function handleChange(e) {
        setValues({
            ...values,
            [e.target.name]: e.target.value,
        })
    }

    const handleSubmitButton = () => {
        if (values.change !== values.confirm) {
            alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.")
        }

        const data = {
            password: values.password,
            changePassword: values.changePassword
        }
        const targetUsername = params.username
        
        request("ADMIN_PATCH_"+targetUsername, data)
        .then(res => {
            console.log(res.data)
        })

        // navigate("/admin/login")
    }

    return (
        <Wrapper>
            <Title>Change Password</Title>
            <Content>
                <Description>original password</Description>
                <InputBox type={"password"} name={"original"} onChange={handleChange} />

                <Empty />

                <Description>change password</Description>
                <InputBox type={"password"} name={"change"} onChange={handleChange} />
                <Description>password confirm</Description>
                <InputBox type={"password"} name={"confirm"} onChange={handleChange} />
                <SubmitButton onClick={handleSubmitButton}>Submit</SubmitButton>
            </Content>
        </Wrapper>
    )
}