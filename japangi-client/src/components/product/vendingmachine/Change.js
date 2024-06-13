import { useEffect, useState } from "react"
import { useNavigate, useParams, useSearchParams } from "react-router-dom"
import styled from "styled-components"

const Wrapper = styled.div`
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
`

const Title = styled.div`
    width: 400px;
    height: 50px;
    font-size: 24px;
    font-weight: 600;
    padding-left: 20px;
    margin-bottom: 5px;
`

const Content = styled.div`
    width: 400px;
    height: 300px;
    background-color: #ffffff;
    border-radius: 18px;
    box-shadow: 20x 8px 12px rgba(1, 1, 1, 0.01);
    margin-bottom: 50px;
`

const Change = styled.div`
    width: 350px;
    height: 190px;
    margin-top: 20px;
    margin-left: 25px;
`

const ChangeElement = styled.div`
    width: 350px;
    margin-top: 10px;
`

const Button = styled.button`
    width: 350px;
    height: 45px;
    margin-left: 25px;
    margin-top: 25px;
    background-color: #F69B0B;
    border: none;
    font-size: 18px;
    font-weight: 500;
    font-weight: 500;
    transition: .2s ease;  
    color: #ffffff;
    border-radius: 14px;

    &:hover {
        opacity: 70%;
    }
`

export default function Changes() {

    const navigate = useNavigate()
    const params = useParams()
    const [searchParams, setSearchParams] = useSearchParams();
    const [changes, setChanges] = useState({})

    useEffect(() => {
        const changeValues = ["10", "50", "100", "500", "1000"]
        var tmpChanges = {
            "10" : 0,
            "50" : 0,
            "100" : 0,
            "500" : 0,
            "1000" : 0,
        }

        changeValues.map(v => {
            if (searchParams.get(v) !== null) {
                tmpChanges[v] = searchParams.get(v)
            }
        })
        setChanges(tmpChanges)
    }, [])

    function handleConfirmButton(e) {
        navigate("/vendingmachine/" + params.vendingmachineId)
    }

    return (
        <Wrapper>
            <Title>
                Changes
            </Title>
            <Content>
                <Change>
                    <ChangeElement>￦ 10 : {changes["10"]}</ChangeElement>
                    <ChangeElement>￦ 50 : {changes["50"]}</ChangeElement>
                    <ChangeElement>￦ 100 : {changes["100"]}</ChangeElement>
                    <ChangeElement>￦ 500 : {changes["500"]}</ChangeElement>
                    <ChangeElement>￦ 1000 : {changes["1000"]} </ChangeElement>
                </Change>
                <Button onClick={handleConfirmButton}>Confirm</Button>
            </Content>
        </Wrapper>
    )
}