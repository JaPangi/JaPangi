import styled from "styled-components"
import { Outlet } from "react-router-dom"

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

export default function Main() {
    return (
        <Wrapper>
            <Card>
                <Outlet /> 
            </Card>
            <CopyRight>
                © 2024. developer @wwan13 @JunRock Co. all rights reserved.
            </CopyRight>
        </Wrapper>
    )
}