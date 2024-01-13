import { Navigate, Outlet } from "react-router-dom";
import { useAuth } from "../authProvider";
import serverUrl from "../Consts/serverUrl";
import axios from "axios";
import { useEffect } from "react";




const Workspace = () => {

    const { token, setToken } = useAuth();


    useEffect(() => {

        // Check if the user is authenticated
        if (!token) {
            // If not authenticated, redirect to the login page
            return <Navigate to="/login" />;
        } else {
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

                    const response = await axios.get(serverUrl + '/work/me');
                    console.log(response);
                } catch (error) {
                    console.error(error);
                }

            }

            const buildWorkspace = () => {

            }

            const parsedToken = parseJwt(token);

            console.log(token);
            console.log(parsedToken);


            if (parsedToken && parsedToken.exp * 1000 > (new Date()).getTime()) {
                const employee = getEmployee();
                buildWorkspace(employee);
            } else {
                console.log("asdasd");
                return <Navigate to="/login" />;
            }


        }
    }, [token, authProvider])

    return (
        <>
            Workspace

            <Outlet></Outlet>
        </>

    );
}

export default Workspace;
