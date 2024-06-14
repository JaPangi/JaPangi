import axios from "axios"

export default function request(key, body) {
    
    const data = {
        key : key,
        body : body
    }

    const headers = {
        "Content-Type": `application/json;charset=UTF-8`,
        "Accept": "application/json",
        "Access-Control-Allow-Origin": `http://localhost:3000`,
        'Access-Control-Allow-Credentials':"true",
    }

    const basedUrl = "http://" + process.env.REACT_APP_TARGET_SERVER_HOST + ":" + process.env.REACT_APP_TARGET_SERVER_PORT
    return axios.post(basedUrl + "/relay", data, headers)
}