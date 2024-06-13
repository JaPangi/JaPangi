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

    return axios.post("http://127.0.0.1:8060/relay", data, headers)
}