import styled from "styled-components"
import { Link, Outlet, useLocation, useNavigate } from "react-router-dom"
import { useEffect } from "react"

const Wrapper = styled.div`
    width : 100vw;
    height : 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #ECEDEF;
`

const Card = styled.div`
    width : 1024px;
    height : 726px;
`

const CopyRight = styled.div`
    position: fixed;
    bottom: 0px;
    width: 100%;
    height: 30px;
    color: gray;
    text-align: right;
    margin-right: 30px;
`

const AdminLink = styled(Link) `
    outline:none;
    color: gray;
    text-decoration: none;
`

const BackButton = styled.button`
    position: fixed;
    top: 15px;
    left: 15px;
    height: 30px;
    color: gray;
    text-align: left;
    outline: none;
    border: none;
    font-size: 30px;
    color: lightgray;
    font-weight: 500;
`

export default function Main() {

    const currentLocation = useLocation()
    const navigate = useNavigate()

    var linkTarget

    if (currentLocation.pathname.startsWith("/admin")) {
        linkTarget = "/vendingmachine/select"    
    } else {
        linkTarget = "/admin/login"    
    }

    const backButtonText = "<"

    function handlebackButton() {
        navigate(-1)
    }

    return (
        <Wrapper>
            <BackButton onClick={handlebackButton}>{backButtonText}</BackButton>
            <Card>
                <Outlet /> 
            </Card>
            <CopyRight>
                <AdminLink to={linkTarget}>
                    © 2024. developer @wwan13 @JunRock Co. all rights reserved.
                </AdminLink>
            </CopyRight>
        </Wrapper>
    )
}