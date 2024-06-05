import ReactModal from "react-modal"
import { styled } from "styled-components"

const modalStyle = {
    overlay: {
        backgroundColor: "rgba(1, 1, 1, 0.4)"
    },
    content: {
        height: "600px",
        width: "800px",
        margin: "auto",
        borderRadius: "20px",
        padding: "20px",
        boxSizing: "border-box",
        boxShadow: "20x 8px 12px rgba(1, 1, 1, 0.01)"
    }
}

const ModalCloseButton = styled.button`
    position: absolute;
    color: lightgray;
    font-size: 24px;
    background-color: #ffffff;
    right: 2%;
    border: none;
    outline: none;
`

const ModalWrapper = styled.div`
    width: 100%;
    height: 100%;
    padding: 20px;
    box-sizing: border-box;
    /* background-color: #123123; */
`

const TitleWrapper = styled.div`
    width: 100%;
    height: 13%;
    font-size: 30px;
    font-weight: 600;
    /* background-color: #321321; */
`

const ContentsWrapper = styled.div`
    width: 100%;
    height: 74%;
    display: flex;
`

const ButtonWrapper = styled.div`
    width: 100%;
    height: 13%;
    font-size: 30px;
    font-weight: 600;
    display: flex;
    align-items: flex-end;
    justify-content: right;
    /* background-color: #321321; */
`

const Button = styled.button`
    width: 120px;
    height: 65%;
    background-color: #F69B0B;
    border-radius: 14px;
    outline: none;
    border: none;
    color: #ffffff;
    font-size: 16px;
    font-weight: 500;
    transition: .2s ease;  

    &:hover {
        opacity: 70%;
    }
`

const DrinkInfoWrapper = styled.div`
    width: 40%;
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    border: 1px solid lightgray;
    border-radius: 20px;
    /* background-color: #123123; */
`

const DrinkImage = styled.img`
    width: 90%;
    height: 300px;
    outline: none;
    border: none;
    border-radius: 12px;
    background-color: lightgray;
    margin-bottom: 10px;
`

const DrinkDateil = styled.div`
    width: 90%;
    height: 25px;
    line-height: 30px;
    text-align: center;
    font-size: 16px;
    color: #1c1b1a;
`

const InsertCoinsWrapper = styled.div`
    width: 60%;
    height: 100%;
    display: flex;
    flex-direction : column;
    align-items: center;
`

const InputStateBox = styled.div`
    width: 60%;
    height: 50px;
    line-height: 50px;
    text-align :center;
    font-size: 20px;
    font-weight: 500;
    color: #1c1b1a;
    border: 2px solid lightgray;
    border-radius: 17px;
    margin-bottom: 20px;
`

const InputBox = styled.div`
    width: 90%;
    height: 42px;
    margin-top: 17px;
    display: flex;
    justify-content: space-between;
    /* background-color: #321312; */
`

const CoinDetail = styled.div`
    width: 30%;
    height: 100%;
    line-height: 42px;
    padding-left: 30px;
    box-sizing: border-box;
    font-size: 18px;
    font-weight: 500;
    color: #1c1b1a;
    /* background-color: #123123; */
`

const CoinInput = styled.div`
    width: 70%;
    height: 100%;
    display: flex;
    justify-content: right;
    padding-right: 30px;
    box-sizing: border-box;
`

const CoinAmountState = styled.div`
    width: 100px;
    height: 40px;
    text-align: center;
    line-height: 40px;
    font-size: 16px;
    font-weight: 500;
    color: #1c1b1a;
`

const CoinInputButton = styled.button`
    width: 40px;
    height: 40px;
    border-radius: 20px;
    background-color: lightgray;
    outline: none;
    border: none;
    font-size: 20px;
    font-weight: 400;
    color: #ffffff;
    transition: .3s ease;

    &:hover {
        background-color: #F69B0B;
    }
`

export default function DrinkPurchaseModal(props) {

    return (
        <ReactModal isOpen={props.isModalOpen} onRequestClose={props.closeModal} style={modalStyle}>
            <ModalCloseButton onClick={props.closeModal}>X</ModalCloseButton>
            <ModalWrapper>
                <TitleWrapper>
                    Insert Coins
                </TitleWrapper>

                <ContentsWrapper>
                    <DrinkInfoWrapper>
                        <DrinkImage />
                        <DrinkDateil>Premium Coffee</DrinkDateil>
                        <DrinkDateil>￦ 2000</DrinkDateil>
                    </DrinkInfoWrapper>
                    <InsertCoinsWrapper>
                        <InputStateBox>
                            ￦ 300
                        </InputStateBox>
                        
                        <InputBox>
                            <CoinDetail>
                                ￦ 10
                            </CoinDetail>
                            <CoinInput>
                                <CoinInputButton>-</CoinInputButton>
                                <CoinAmountState>2</CoinAmountState>
                                <CoinInputButton>+</CoinInputButton>
                            </CoinInput>
                        </InputBox>

                        <InputBox>
                            <CoinDetail>
                                ￦ 50
                            </CoinDetail>
                            <CoinInput>
                                <CoinInputButton>-</CoinInputButton>
                                <CoinAmountState>2</CoinAmountState>
                                <CoinInputButton>+</CoinInputButton>
                            </CoinInput>
                        </InputBox>

                        <InputBox>
                            <CoinDetail>
                                ￦ 100
                            </CoinDetail>
                            <CoinInput>
                                <CoinInputButton>-</CoinInputButton>
                                <CoinAmountState>2</CoinAmountState>
                                <CoinInputButton>+</CoinInputButton>
                            </CoinInput>
                        </InputBox>

                        <InputBox>
                            <CoinDetail>
                                ￦ 500
                            </CoinDetail>
                            <CoinInput>
                                <CoinInputButton>-</CoinInputButton>
                                <CoinAmountState>2</CoinAmountState>
                                <CoinInputButton>+</CoinInputButton>
                            </CoinInput>
                        </InputBox>

                        <InputBox>
                            <CoinDetail>
                                ￦ 1000
                            </CoinDetail>
                            <CoinInput>
                                <CoinInputButton>-</CoinInputButton>
                                <CoinAmountState>2</CoinAmountState>
                                <CoinInputButton>+</CoinInputButton>
                            </CoinInput>
                        </InputBox>
                    </InsertCoinsWrapper>
                </ContentsWrapper>

                <ButtonWrapper>
                    <Button>
                        Purchase
                    </Button>
                </ButtonWrapper>
            </ModalWrapper>
        </ReactModal>
    )
}