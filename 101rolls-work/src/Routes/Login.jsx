import { useState } from 'react';
import { useNavigate } from "react-router-dom";
import axios from 'axios';
import { Toaster, useToaster } from '@gravity-ui/uikit';
import { } from '@gravity-ui/icons';
import { useAuth } from "../authProvider";
import serverUrl from '../Consts/serverUrl';


const Login = ({ }) => {

    const navigate = useNavigate();
    const [login, setLogin] = useState('');
    const [password, setPassword] = useState('');

    const { setToken } = useAuth();

    const { add } = useToaster();

    const onChangeInput = (e) => {
        if (e.target.id === "password") {
            setPassword(e.target.value);
        }
        if (e.target.id === "login") {
            setLogin(e.target.value);
        }
    }

    const makeLogin = async () => {

        function uuidv4() {
            return "10000000-1000-4000-8000-100000000000".replace(/[018]/g, c =>
                (c ^ crypto.getRandomValues(new Uint8Array(1))[0] & 15 >> c / 4).toString(16)
            );
        }

        const toaster = new Toaster();
        toaster.name = uuidv4();
        toaster.type = "error";

        if (login == '' || password == '') {
            toaster.title = "Введите данные для входа";
            add(toaster);
            return;
        }

        let resp;

        try {
            resp = await axios.post(serverUrl + "/auth/employee", { login: login, password: password });

        } catch (error) {
            resp = error.response;
        }

        console.log(resp);

        if (resp != undefined) {
            switch (resp.status) {
                case 200:
                    toaster.title = "Привет";
                    toaster.type = "success";
                    setToken(resp.data.access_token);
                    navigate("/workspace");
                    break;
                case 403:
                    toaster.title = "Неверный логин или пароль";
                    break;
                case 500:
                    toaster.title = "Внутренняя ошибка сервера";
                    break;
                default:
                    toaster.title = "Неизвестная ошибка";
                    break;
            }


        } else {
            toaster.title = "Ошибка соединения";
        }

        if (toaster.title != undefined) {
            add(toaster);
        }

    }



    return (
        <>
            <header className="auth_header">
                <div className="header_container">
                    <img src="logo.png"></img>
                </div>

            </header>

            <div className="auth_container">

                <h2>Вход в систему</h2>

                <div>

                    <div className="auth_input_container">
                        <div className="auth_control">
                            <label htmlFor="login">Логин:</label>
                            <input type="text" name="login" id="login" className="auth_input" value={login} onChange={e => onChangeInput(e)}></input>
                        </div>

                        <div className="auth_control">
                            <label htmlFor="password">Пароль:</label>
                            <input type="password" name="password" id="password" className="auth_input" value={password} onChange={e => onChangeInput(e)}></input>
                        </div>
                    </div>

                    <button onClick={makeLogin} className="btn-submit">Войти</button>
                </div>

            </div>
        </>
    );
}

export default Login;
