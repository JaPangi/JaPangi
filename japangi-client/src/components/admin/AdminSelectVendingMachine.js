import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"
import styled from "styled-components"
import request from "../../request/Request"

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
`

const Content = styled.div`
    width: 100%;
    height: 85%;
    background-color: #ffffff;
    border-radius: 17px;
    box-shadow: 2px 8px 12px rgba(1, 1, 1, 0.02);
    padding: 40px;
    box-sizing: border-box;
    overflow: scroll;
    padding: 30px;
    box-sizing: border-box;
`

const VendingMachineElement = styled.button`
    width: 100%;
    height: 80px;
    border: 2px solid lightgray;
    background-color: #ffffff;
    border-radius: 10px;
    transition: .2s ease;
    font-size: 19px;
    color: gray;
    margin-bottom: 20px;

    &:hover {
        border: 2px solid #F69B0B;
        transform: translateY(-3%);
    }
`

const VendingMachineAddButton = styled.button`
    width: 100%;
    height: 80px;
    border: 2px dotted lightgray;
    background-color: #ffffff;
    border-radius: 10px;
    transition: .2s ease;
    font-size: 19px;
    color: lightgray;
    margin-bottom: 20px;
    margin-top: 30px;

    &:hover {
        border: 2px dotted #F69B0B;
        transform: translateY(-3%);
    }
`

export default function AdminSelectVendingMachine() {

    const navigate = useNavigate();
    const [username, setUsername] = useState("");
    const [vendingMachines, setVendingMachine] = useState([])
    
    useEffect(() => {
        const user = document.cookie.match("user")["input"].substring(5)
        setUsername(user)

        request("VENDING_MACHINE_ALL", null)
        .then(res => {
            setVendingMachine(res.data.data)
        })
    }, [])

    const HandleButtonClick = (e) => {
        navigate("/admin/vendingmachine/" + e.target.name)
    }

    const HandleAdminButtonClick = (e) => {
        if (window.confirm("Would you like to go to the admin password change page?")) {
            navigate("/admin/password/change")
        } else {
        }
    }

    const HandleVendingMachineAddButton = (e) => {
        if (window.confirm("Would you like to add a vending machine?")) {
            
        }
    }

    return (
        <Wrapper>
            <Title>
                <AdminTitle onClick={HandleAdminButtonClick}>{username}</AdminTitle>
                <ContentTitle>Select Vending Machie For Managing</ContentTitle>
            </Title>
            <Content>
                {
                    vendingMachines.map(vm => {
                        return <VendingMachineElement onClick={HandleButtonClick} name={vm.vendingMachineId}>Vending Machine #{vm.vendingMachineId}</VendingMachineElement>
                    })
                }

                <VendingMachineAddButton onClick={HandleVendingMachineAddButton}>+</VendingMachineAddButton>
            </Content>
        </Wrapper>
    )
}