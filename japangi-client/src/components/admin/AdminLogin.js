import { useEffect, useState } from "react"
import { Link, useNavigate } from "react-router-dom"
import styled from "styled-components"
import request from "../../request/Request"

const Wrapper = styled.div`
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
`

const ContentBox = styled.form`
    width: 45%;
    height: 43%;
    border-radius: 17px;
    margin-bottom: 50px;
`

const LonginTitle = styled.div`
    width: 100%;
    height: 78px;
    line-height: 78px;
    padding-left: 15px;
    font-size: 23px;
    font-weight: 600;
`

const LonginBox = styled.div`
    width: 100%;
    height: 82%;
    background-color: #ffffff;
    border-radius: 17px;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
`

const InputBox = styled.div`
    width: 80%;
    display: flex;
    justify-content: space-between;
    height: 45px;
    line-height: 45px;
    font-size: 20px;
    font-weight: 500;
    color: gray;
    margin-top: 7px;
`

const Input = styled.input`
    width: 86%;
    height: 30px;
    border: none;
    border-bottom: 2px solid lightgray;
    outline: none;
    padding-left: 5px;
`

const SubmitButton = styled.button`
    width: 80%;
    height: 40px;
    background-color: #F69B0B;
    border-radius: 8px;
    outline: none;
    border: none;
    color: #ffffff;
    font-size: 20px;
    font-weight: 500;
    transition: .2s ease;  
    margin-top: 30px;

    &:hover {
        opacity: 70%;
    }
`

export default function AdminLogin() {

    const navigate = useNavigate()
    const [values, setValues] = useState({
        id: "",
        pw: ""
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

    function handleLoginbutton(e) {
        e.preventDefault()
        const data = {
            username : values.id,
            password : values.pw
        }

        request("ADMIN_LOGIN", data)
        .then(res => {
            if (res.data.status === "ERROR") {
                alert(res.data.message)
            } else {
                document.cookie = "user=" + res.data.data.username + ';'
                navigate("/admin/vendingmachine/select")
            }
        })
    }

    return (
        <Wrapper>
            <ContentBox>
                <LonginTitle>
                    Admin Login
                </LonginTitle>
                <LonginBox>
                    <InputBox>
                        ID
                        <Input name = "id" onChange={handleChange} />
                    </InputBox>
                    <InputBox>
                        PW
                        <Input type={"password"} name = "pw" onChange={handleChange} />
                    </InputBox>
                    <SubmitButton onClick={handleLoginbutton} onChange={handleChange}>login</SubmitButton>
                </LonginBox>
            </ContentBox>
        </Wrapper>
    )
}