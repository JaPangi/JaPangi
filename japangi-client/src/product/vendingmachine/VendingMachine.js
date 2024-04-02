import { useState } from "react"
import styled from "styled-components"
import ReactModal from "react-modal"
import DrinkPurchaseModal from "./DrinkPurchaseModal"

const Wrapper = styled.div`
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    box-sizing: border-box;
` 

const TitleWraper = styled.div`
    width: 100%;
    height: 12%;
    background-color: #ffffff;
    border-radius: 20px;
    display:flex;
    align-items: center;
    box-shadow: 2px 8px 12px rgba(1, 1, 1, 0.02);
`

const Title = styled.p`
    font-size: 28px;
    font-weight: 600;
    margin-top: 3px;
    padding-left: 50px;
    box-sizing: border-box;
`

const MenuWrapper = styled.div`
    width: 100%;
    height: 74%;
    display: flex;
    justify-content: space-between;
    align-items: center;
    flex-wrap: wrap;
    background-color: #ffffff;
    border-radius: 20px;
    padding: 50px;
    box-sizing: border-box;
    box-shadow: 2px 8px 12px rgba(1, 1, 1, 0.02);
`

const MenuElement = styled.div`
    width: 280px;
    height: 185px;
    border-radius: 10px;
    border: 2px solid ${(props) => props.borderColor};
    padding: 15px;
    box-sizing: border-box;
    display: flex;
    justify-content: space-between;
`

const MenuImage = styled.img`
    width: 47%;
    height: 100%;
    background-color: lightgray;
    outline: none;
    border: none;
    border-radius: 5px;
`

const MenuDetail = styled.div`
    width: 47%;
    height: 100%;
`

const MenuName = styled.div`
    font-size: 22px;
    font-weight: 500;
    height: 60%;
    display: flex;
    align-items: center;
    padding-bottom: 10px;
    box-sizing: border-box;
`

const MenuInfo = styled.div`
    font-size: 16px;
    margin-bottom: 3px;
`

const ButtonWrapper = styled.div`
    width: 100%;
    height: 8%;
    display: flex;
    justify-content: right;
    align-items: flex-end;
`

const Button = styled.button`
    width: 150px;
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

const modalStyle = {
    overlay: {
        backgroundColor: "rgba(1, 1, 1, 0.5)"
    },
    content: {
        height: "600px",
        width: "800px",
        margin: "auto",
        borderRadius: "20px",
        padding: "20px",
        boxSizing: "border-box"
    }
}

const ModalCloseButton = styled.button`
    position: absolute;
    color: lightgray;
    font-size: 24px;
    background-color: #ffffff;
    border: none;
    outline: none;
`

export default function VendingMachine() {

    const [isModalOpen, setIsModalOpen] = useState(false)

    const openModal = () => {
        setIsModalOpen(true)
    }

    const closeModal = () => {
        setIsModalOpen(false)
    }

    return (
        <Wrapper>
            <TitleWraper>
                <Title>Select Drink</Title>
            </TitleWraper>
            <MenuWrapper>
                <MenuElement borderColor = {"#D3D3D3"}>
                    <MenuImage />
                    <MenuDetail>
                        <MenuName>Water</MenuName>
                        <MenuInfo>￦ 450</MenuInfo>
                        <MenuInfo>6 left</MenuInfo>
                    </MenuDetail>
                </MenuElement>
                <MenuElement borderColor = {"#D3D3D3"}>
                <MenuImage />
                    <MenuDetail>
                        <MenuName>Preminum<br/>Coffee</MenuName>
                        <MenuInfo>￦ 700</MenuInfo>
                        <MenuInfo>6 left</MenuInfo>
                    </MenuDetail>
                </MenuElement>
                <MenuElement borderColor = {"#F69B0B"}>

                </MenuElement>
                <MenuElement borderColor = {"#D3D3D3"}>

                </MenuElement>
                <MenuElement borderColor = {"#D3D3D3"}>

                </MenuElement>
                <MenuElement borderColor = {"#D3D3D3"}>

                </MenuElement>
            </MenuWrapper>
            <ButtonWrapper>
                <Button onClick={openModal}>
                    Select
                </Button>
            </ButtonWrapper>

            <DrinkPurchaseModal isModalOpen={isModalOpen} closeModal={closeModal} />
        </Wrapper>
    )
}
