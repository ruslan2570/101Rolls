import { Container, Row, Col, Flex, Button, spacing, Icon } from '@gravity-ui/uikit';
import { Gear} from '@gravity-ui/icons';
  


function Login() {
  return (
    <>
      <header className="auth_header">
        <div className="header_container">
            <img src="logo.png"></img>
        </div>
        
    </header>

    <div className="auth_container">

        <h2>Вход в систему</h2>

        <form action="auth.html" method="post">

            <div className="auth_input_container">
                <div className="auth_control">
                    <label htmlFor="login">Логин:</label>
                    <input type="text" name="login" id="login" className="auth_input"></input>
                </div>

                <div className="auth_control">
                    <label htmlFor="password">Пароль:</label>
                    <input type="password" name="password" id="password" className="auth_input"></input>
                </div>
            </div>

            <input type="submit" value="Войти" className="btn-submit"></input>
        </form>
       
    </div>
    </>
  );
}

export default Login;
