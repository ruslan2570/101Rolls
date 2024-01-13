import '@gravity-ui/uikit/styles/fonts.css';
import '@gravity-ui/uikit/styles/styles.css';

import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import { BrowserRouter } from 'react-router-dom';
import { ToasterComponent, ToasterProvider } from '@gravity-ui/uikit';
import AuthProvider from './authProvider';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <AuthProvider>
    <BrowserRouter>
      <ToasterProvider>
        <App />
        <ToasterComponent className="optional additional classes g-root g-root_theme_light" />
      </ToasterProvider>
    </BrowserRouter>
  </AuthProvider>

);
