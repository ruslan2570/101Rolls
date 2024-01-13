import { Route, Routes } from 'react-router-dom';
import Login from './Routes/Login.jsx';
import Workspace from './Routes/Workspace.jsx';
import AuthProvider from './AuthProvider.js';

function App() {
  return (

    <AuthProvider>


      <Routes>
        <Route path='/login' element={<Login />}></Route>
        <Route path='/*' element={<Workspace />}>
          <Route path='dashboard' element={<> dashboard </>}></Route>
        </Route>
      </Routes>
    </AuthProvider>

  );
}

export default App;
