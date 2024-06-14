import { useEffect, useState } from "react"
import { useNavigate, useParams } from "react-router-dom"
import styled from "styled-components"
import IsAdminLoggedIn from "../../login/IsAdminLoggedIn"
import request from "../../request/Request"

const Wrapper = styled.div`
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
`

const Content = styled.form`
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

    IsAdminLoggedIn()

    useEffect(() => {
    })

    function handleChange(e) {
        setValues({
            ...values,
            [e.target.name]: e.target.value,
        })
    }

    const handleSubmitButton = (e) => {
        e.preventDefault()

        if (values.change !== values.confirm) {
            alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.")
        }

        console.log(values.original)

        const data = {
            password: values.original,
            changePassword: values.change
        }
        const targetUserId = window.sessionStorage.getItem("userid")
        
        request("ADMIN_PATCH_"+targetUserId, data)
        .then(res => {
            if (res.data.status == "ERROR") {
                alert(res.data.message)
            }
            alert("비밀번호가 변경되었습니다.")
            navigate("/admin/login")
        })
    }

    return (
        <Wrapper>
            <Title>Change Password</Title>
            <Content>
                <Description>original password</Description>
                <InputBox type={"password"} name={"original"} onChange={handleChange} value={values.original} />

                <Empty />

                <Description>change password</Description>
                <InputBox type={"password"} name={"change"} onChange={handleChange} value={values.change} />
                <Description>password confirm</Description>
                <InputBox type={"password"} name={"confirm"} onChange={handleChange} value={values.confirm} />
                <SubmitButton onClick={handleSubmitButton}>Submit</SubmitButton>
            </Content>
        </Wrapper>
    )
}