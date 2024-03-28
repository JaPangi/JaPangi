import { useNavigate } from "react-router-dom"
import styled from "styled-components"

const Wrapper = styled.div`
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: space-between;
` 

const TitleWrapper = styled.div`
    width: 30%;
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    background-color: #ffffff;
    border-radius: 20px;
    box-shadow: 2px 8px 12px rgba(1, 1, 1, 0.02);
`

const Title = styled.div`
    width: 100%;
    text-align: center;
    margin: 5px 0px 5px 0px;
    font-size: 30px;
    font-weight: 600;
`

const SelectWrapper = styled.div`
    width: 67%;
    height: 100%;
    display: flex;
    justify-content: space-between;
    flex-direction: column;
`

const ListWrapper = styled.div`
    width: 100%;
    height: 87%;
    border-radius: 20px;
    background-color: #ffffff;
    padding: 35px 30px 35px 30px;
    box-sizing: border-box;
    box-shadow: 2px 8px 12px rgba(1, 1, 1, 0.02);
`

const ListElement = styled.div`
    width: 98%;
    height: 60px;
    line-height: 60px;
    text-align: center;
    border: 2px solid ${(props) => props.borderColor};
    border-radius: 10px;
    font-size: 18px;
    font-weight: 500;
    color: gray;
    margin-bottom: 14px;
    transition: .2s ease;   

    &:hover {
        transform: translateY(-3%);
    }
`

const ButtonWrapper = styled.div`
    width: 100%;
    height: 9%;
    display: flex;
    justify-content: right;
    align-items: flex-end;
`

const SubmitButton = styled.button`
    width: 140px;
    height: 100%;
    background-color: #F69B0B;
    border-radius: 17px;
    outline: none;
    border: none;
    color: #ffffff;
    font-size: 20px;
    font-weight: 500;
    transition: .2s ease;  

    &:hover {
        opacity: 70%;
    }
`

export default function SelectVendingMachine() {

    const navigate = useNavigate();

    const handleSubmit = function(e) {
        e.preventDefault();
        navigate("/vendingmachine/1")
    }

    return (
        <Wrapper>
            <TitleWrapper>
                <Title>Select</Title>
                <Title>Vending</Title>
                <Title>Machine</Title>
            </TitleWrapper>
            <SelectWrapper>
                <ListWrapper>
                    <ListElement borderColor = {"#F69B0B"}>vending machine #1</ListElement>
                    <ListElement borderColor = {"#D3D3D3"}>vending machine #2</ListElement>
                </ListWrapper>
                <ButtonWrapper>
                    <SubmitButton onClick={handleSubmit}>select</SubmitButton>
                </ButtonWrapper>
            </SelectWrapper>
        </Wrapper>
    )
}
