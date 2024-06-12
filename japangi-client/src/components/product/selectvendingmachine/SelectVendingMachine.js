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
    height: 100%;
    border-radius: 20px;
    background-color: #ffffff;
    padding: 35px 30px 35px 30px;
    box-sizing: border-box;
    box-shadow: 2px 8px 12px rgba(1, 1, 1, 0.02);
`

const ListElement = styled.button`
    width: 98%;
    height: 60px;
    line-height: 60px;
    text-align: center;
    border: 2px solid #D3D3D3;
    border-radius: 10px;
    font-size: 18px;
    font-weight: 500;
    color: gray;
    margin-bottom: 14px;
    transition: .2s ease;   
    background-color: #ffffff;
    outline: none;

    &:hover {
        transform: translateY(-3%);
        border: 2px solid #F69B0B;
    }
`

export default function SelectVendingMachine() {

    const navigate = useNavigate();

    const handleElementButton = function(e) {
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
                    <ListElement onClick={handleElementButton} borderColor = {"#F69B0B"}>vending machine #1</ListElement>
                    <ListElement onClick={handleElementButton} borderColor = {"#D3D3D3"}>vending machine #2</ListElement>
                </ListWrapper>
            </SelectWrapper>
        </Wrapper>
    )
}
