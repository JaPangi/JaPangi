import styled from "styled-components"
import { useNavigate, useParams } from "react-router-dom"
import { useEffect, useState } from "react"
import request from "../../../request/Request"

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
    height: 82%;
    display: flex;
    align-items: center;
    flex-wrap: wrap;
    background-color: #ffffff;
    border-radius: 20px;
    padding: 50px;
    box-sizing: border-box;
    box-shadow: 2px 8px 12px rgba(1, 1, 1, 0.02);
    overflow: scroll;
    flex-direction: row;
`

const MenuElement = styled.button`
    width: 280px;
    height: 185px;
    border-radius: 10px;
    border: 2px solid #D3D3D3;
    padding: 15px;
    box-sizing: border-box;
    display: flex;
    justify-content: space-between;
    transition: .2s ease;
    background-color: #ffffff;
    margin: 27px 14px 27px 14px;

    &:hover {
        border: 2px solid #F69B0B;
        transform: translateY(-2%);
    }
`

const MenuImage = styled.img`
    width: 47%;
    height: 100%;
    background-color: #ffffff;
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

export default function VendingMachine() {

    const params = useParams()
    const navigate = useNavigate()
    const [drinks, setDrinks] = useState([])

    useEffect(() => {
        request("VENDING_MACHINE_INFO_" + params.vendingmachineId, null)
        .then(res => {
            setDrinks(res.data.data)
        })
    }, [])

    const handleButton = (e) => {
        navigate("/vendingmachine/"+ params.vendingmachineId +"/drink/"+ parseInt(e.target.id) +"/purchase")
    }

    return (
        <Wrapper>
            <TitleWraper>
                <Title>Select Drink</Title>
            </TitleWraper>
            <MenuWrapper>
                {
                    drinks.map(d => {
                        return (
                            <MenuElement onClick={handleButton} id={d.drinkId}>
                                <MenuImage src={d.drinkImageUrl} id={d.drinkId} />
                                <MenuDetail id={d.drinkId}>
                                    <MenuName id={d.drinkId}>{d.drinkName}</MenuName>
                                    <MenuInfo id={d.drinkId}>￦ {d.drinkPrice}</MenuInfo>
                                    <MenuInfo id={d.drinkId}>{d.stock} left</MenuInfo>
                                </MenuDetail>
                            </MenuElement>
                        )
                    })
                }
            </MenuWrapper>
        </Wrapper>
    )
}
