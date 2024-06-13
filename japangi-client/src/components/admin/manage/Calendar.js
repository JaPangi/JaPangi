import styled from "styled-components"

const Wrapper = styled.div`
    width: 660px;
    height: 426px;
    display: flex;
    flex-wrap: wrap;
    align-items: center;
`

const RealElement = styled.div`
    width:90px;
    height:67px;
    border: 1px solid lightgray;
    border-radius: 5px;
    box-sizing: border-box;
    margin: 2px;
    padding: 3px;
`

const DisplayDate = styled.div`
    width:13px;
    height:13px;
    font-size: 13px;
    color: gray;
    margin-bottom: 7px;
`

const TodayElement = styled.div`
    width:90px;
    height:67px;
    border: 1px solid #F69B0B;
    border-radius: 5px;
    box-sizing: border-box;
    margin: 2px;
    padding: 3px;
`

const FakeElement = styled.div`
    width:94px;
    height:71px;
    border: 1px solid white;
    box-sizing: border-box;
`

const Detail = styled.div`
    width: 100%;
    height: 15px;
    background-color: lightgoldenrodyellow;
    margin-top: 3px;
    font-size: 10px;
    line-height: 15px;
    border-radius: 2px;
`

const EmptyDetail = styled.div`
    width: 100%;
    height: 15px;
    background-color: #ffffff;
    margin-top: 3px;
    font-size: 10px;
    line-height: 15px;
`

export default function Calendar(props) {

    const data = props.data

    const endDayOfMonth = new Date(props.year, props.month, 0).getDate()
    var dates = []
    
    for(var i=1;i<endDayOfMonth + 1;i++) {

        var isChanged = false

        var target = props.year + "-" + props.month + "-" + i
        if (props.month < 10) {
            target = props.year + "-0" + props.month + "-" + i
        }
        if (i < 10) {
            target = props.year + "-0" + props.month + "-0" + i
        }
        
        for(var j=0;j<data.length;j++) {
            const element = data[j]

            if (element.date === target) {
                isChanged = true
                const data = {
                    day: i,
                    price : element.totalPrice,
                    amount : element.totalAmount
                }
                dates.push(data)
                break
            }
        }

        if (!isChanged) {
            const data = {
                day: i,
                price : 0,
                amount : 0
            }
            dates.push(data)
        }
    }

    const startDayOfMonth = new Date(props.year+ "-" +props.month + "-1").getDay()
    var fakeDates = []
    for(var i=0;i<startDayOfMonth;i++) {
        fakeDates.push(i)
    }

    const today = new Date()
    const isToday = (d) => {
        return today.getFullYear() === props.year && today.getMonth() + 1 === props.month && today.getDate() === d
    }

    return (
        <Wrapper>
            {
                fakeDates.map(d => {
                    return <FakeElement />
                })
            }
            {
                dates.map(d => {
                    if (isToday(d.day)) {
                        return (
                            <TodayElement>
                                <DisplayDate>{d.day}</DisplayDate>
                                <Detail>Order : {d.amount}</Detail>
                                <Detail>Price : {d.price}</Detail>
                            </TodayElement>    
                        )
                    }
                    if (d.amount == 0) {
                        return (
                            <RealElement>
                                <DisplayDate>{d.day}</DisplayDate>
                                <EmptyDetail></EmptyDetail>
                                <EmptyDetail></EmptyDetail>
                            </RealElement>
                        )
                    } 
                    return (
                        <RealElement>
                            <DisplayDate>{d.day}</DisplayDate>
                            <Detail>Order : {d.amount}</Detail>
                            <Detail>Price : {d.price}</Detail>
                        </RealElement>
                    )
                })
            }
        </Wrapper>
    )
}