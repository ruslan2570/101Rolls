import { useEffect } from "react";
import { Navigate, Outlet } from "react-router-dom";
import { useAuth } from "../authProvider";
import serverUrl from "../Consts/serverUrl";
import axios from "axios";
import {useToaster, Toaster } from "@gravity-ui/uikit";
import {uuidv4} from "../util";


const Workspace = () => {

    const { token, setToken } = useAuth();

    const {add: addToaster } = useToaster();

    const axiosInstance = axios.create({
        baseURL: serverUrl,
        timeout: 1000
      });

    useEffect(() => {

        // Check if the user is authenticated
        if (!token) {
            // If not authenticated, redirect to the login page
            return <Navigate to="/login" />;
        }

        axiosInstance.defaults.headers.common["Authorization"] = "Bearer " + token;

        function parseJwt(token) {
            try {
                var base64Url = token.split('.')[1];
                var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
                var jsonPayload = decodeURIComponent(window.atob(base64).split('').map(function (c) {
                    return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
                }).join(''));

                return JSON.parse(jsonPayload);
            } catch {
                return false;
            }

        }

        const getEmployee = async () => {

            try {

                const response = await axiosInstance.get('/work/me');
                return response.data;
            } catch{
                const toaster = new Toaster();
                toaster.name = uuidv4();
                toaster.title = "Ошибка выполнения запроса"
                toaster.type = "error";
                addToaster(toaster);
                return undefined;
            }

        }

        const buildWorkspace = async (employeePromise) => {
            let employee = await employeePromise;
            if(employee == null){
                const toaster = new Toaster();
                toaster.name = uuidv4();
                toaster.title = "Не удалось получить данные сотрудника"
                toaster.type = "error";
                addToaster(toaster);
                return;
            }

            console.log(employee);
        }

        const parsedToken = parseJwt(token);

        console.log(token);
        console.log(parsedToken);


        if (parsedToken && parsedToken.exp * 1000 > (new Date()).getTime()) {
            const employeePromise = getEmployee();
            buildWorkspace(employeePromise);
        } else {
            console.log("asdasd");
            return <Navigate to="/login" />;
        }



    }, [token])

    return (
        <>
            Workspace

            <Outlet></Outlet>
        </>

    );
}

export default Workspace;
