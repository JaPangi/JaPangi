import { Link, useLocation, useNavigate, useParams } from "react-router-dom"
import styled from "styled-components"
import { useState, useEffect } from "react"
import request from "../../../request/Request"
import IsAdminLoggedIn from "../../../login/IsAdminLoggedIn"

const Wrapper = styled.div`
    width: 100%;
    height: 100%;
    border-radius: 17px;
    display: flex;
    justify-content: space-between;
    flex-direction: column;
`

const Title = styled.div`
    width: 100%;
    height: 12%;
    display: flex;
    justify-content: space-between;
`

const AdminTitle = styled.button`
    width: 20%;
    height: 87px;
    box-sizing: border-box;
    background-color: #ffffff;
    border-radius: 17px;
    text-align: center;
    line-height: 87px;
    font-size: 20px;
    font-weight: 600;
    box-shadow: 2px 8px 12px rgba(1, 1, 1, 0.02);
    border: none;
`

const ContentTitle = styled.div`
    width: 78%;
    height: 87px;
    padding-left: 35px;
    box-sizing: border-box;
    background-color: #ffffff;
    border-radius: 17px;
    line-height: 82px;
    font-size: 20px;
    font-weight: 500;
    box-shadow: 2px 8px 12px rgba(1, 1, 1, 0.02);
    display: flex;
    justify-content: space-between;
    align-items: center;
`

const ContentName = styled.div`
`

const DeleteButton = styled.button`
    width: 100px;
    height: 30px;
    margin-right: 35px;
    color: gray;
    background-color: #ffffff;
    border: none;
    font-size: 14px;
`

const Content = styled.div`
    width: 100%;
    height: 85%;
    background-color: #ffffff;
    border-radius: 17px;
    box-shadow: 2px 8px 12px rgba(1, 1, 1, 0.02);
    padding: 40px;
    box-sizing: border-box;
    display: flex;
    justify-content: space-around;
    align-items: center;
`

const Image = styled.img`
    border: 1px solid lightgrey;
    border-radius: 20px;
    width: 40%;
    height: 90%;
`

const ModifyWrapper = styled.form`
    width: 57%;
    height: 90%;
    padding-left: 50px;
    padding-right: 20px;
    box-sizing: border-box;
    color: gray;
`

const Description = styled.div`
    width: 100%;
    font-size: 18px;
    font-weight: 500;
    margin-bottom: 10px;
    padding-left: 3px;
`

const Input = styled.input`
    width: 100%;
    height: 45px;
    border: 2px solid lightgray;
    border-radius: 12px;
    padding-left: 20px;
    padding-right: 20px;
    box-sizing: border-box;
    outline: none;
    margin-bottom: 23px;
`

const Button = styled.button`
    width: 100%;
    height: 50px;
    border-radius: 12px;
    padding-left: 20px;
    padding-right: 20px;
    box-sizing: border-box;
    outline: none;
    border: none;
    color: #ffffff;
    background-color: #F69B0B; 
    margin-top: 38px;
    font-size: 16px;
    font-weight: 600;
    transition: .2s ease; 

    &:hover {
        opacity: 70%;
    }
`

export default function ManageDrinkDetail() {

    const navigate = useNavigate();
    const params = useParams()
    const [username, setUsername] = useState("")

    const [drink, setDrink] = useState({})
    const [stock, setStock] = useState(0)

    IsAdminLoggedIn()
    
    useEffect(() => {
        const user = window.sessionStorage.getItem("username")
        setUsername(user)

        request("DRINK_GET_" + params.drinkId, null)
        .then(res => {
            if (res.data.status === "ERROR") {
                alert(res.data.message)
                return
            }
            setDrink(res.data.data)

            request("ADMIN_GET_STOCK_" + params.drinkId + "_" + params.vendingmachineId, null)
            .then(res => {
                setStock(res.data.data.amount)
            })
        })
    }, [])

    const HandleAdminButtonClick = (e) => {
        if (window.confirm("Would you like to go to the admin password change page?")) {
            navigate("/admin/password/change/" + username)
        } else {
        }
    }

    function handleChange(e) {
        setDrink({
            ...drink,
            [e.target.name]: e.target.value,
        })
    }

    function handleStockChange(e) {
        setStock(e.target.value)
    }

    function handleApplyButton(e) {
        e.preventDefault()

        const drinkData = {
            drinkName: drink.drinkName,
            drinkPrice: parseInt(drink.drinkPrice),
            drinkImageUrl: drink.imageUrl
        }
        request("ADMIN_PATCH_VENDING_MACHINE_" + parseInt(params.drinkId), drinkData)
        .then(res => {
            if (res.data.status === "ERROR") {
                alert(res.data.message)
            }

            const stockData = {
                drinkId: params.drinkId,
                amount: stock
            }
            request("ADMIN_ADD_DRINK_" + params.vendingmachineId, stockData)
            .then(res => {
                console.log(res)
                if (res.data.status == "ERROR") {
                    alert(res.data.message)
                }
            })
    
            navigate("/admin/vendingmachine/" + params.vendingmachineId + "/manage/drink")
        })
    }

    return (
        <Wrapper>
            <Title>
                <AdminTitle onClick={HandleAdminButtonClick}>{username}</AdminTitle>
                <ContentTitle>
                    <ContentName>Vending Machine #{params.vendingmachineId} / drink / {drink.drinkName}</ContentName>
                    <DeleteButton></DeleteButton>
                </ContentTitle>
            </Title>
            <Content>
                <Image src={drink.imageUrl} />
                <ModifyWrapper>
                    <Description>Name</Description>
                    <Input onChange={handleChange} value={drink.drinkName} name={"drinkName"} />

                    <Description>Image</Description>
                    <Input onChange={handleChange} value={drink.imageUrl} name={"imageUrl"} />

                    <Description>Price (￦)</Description>
                    <Input onChange={handleChange} value={drink.drinkPrice} name={"drinkPrice"} />

                    <Description>Stock</Description>
                    <Input onChange={handleStockChange} value={stock} type="number" min={stock} />

                    <Button onClick={handleApplyButton}>Apply</Button>
                </ModifyWrapper>
            </Content>
        </Wrapper>
    )
}