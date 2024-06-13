import { useNavigate } from "react-router-dom";

export default function IsAdminLoggedIn() {
    const navigate = useNavigate()

    if (window.sessionStorage.getItem("username") === undefined || window.sessionStorage.getItem("userId") === undefined) {
        navigate("/admin/login")
    }
}