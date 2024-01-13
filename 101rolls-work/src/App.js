import { Route, Routes } from 'react-router-dom';
import Login from './Routes/Login.jsx';
import Workspace from './Routes/Workspace.jsx';
import AuthProvider from './authProvider.js';

function App() {
  return (

      <Routes>
        <Route path='/login' element={<Login />}></Route>
        <Route path='/*' element={<Workspace />}>
          <Route path='dashboard' element={<> dashboard </>}></Route>
        </Route>
      </Routes>

  );
}

export default App;
