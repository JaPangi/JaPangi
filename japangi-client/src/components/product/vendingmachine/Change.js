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

const Button = styled.button`
    width: 350px;
    height: 55px;
    margin-left: 25px;
    margin-top: 15px;
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

    function handleConfirmButton(e) {
        navigate("/vendingmachine/3")
    }

    return (
        <Wrapper>
            <Title>
                Changes
            </Title>
            <Content>
                <Change>
                    
                </Change>
                <Button onClick={handleConfirmButton}>Confirm</Button>
            </Content>
        </Wrapper>
    )
}