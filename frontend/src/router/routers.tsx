import {RouteObject} from 'react-router-dom';
import {LoginPage} from '../pages/LoginPage';
import {DashboardPage} from '../pages/DashboardPage';
import {ProtectedRoute} from './ProtectedRoute';
import {Layout} from '../shared/ui/Layout';
import {NotFoundPage} from "../pages/NotFoundPage";
import {RegisterPage} from "../pages/RegisterPage";

export const routes: RouteObject[] = [
  {
    path: '/',
    element: (
      <ProtectedRoute>
        <Layout/>
      </ProtectedRoute>
    ),
    children: [
      {index: true, element: <DashboardPage/>},
      {path: 'dashboard', element: <DashboardPage/>},
      // {path: '*', element: <NotFoundPage/>},
    ]
  },
  {
    path: '/login',
    element: <LoginPage/>,
  },
  {
    path: '/register',
    element: <RegisterPage/>,
  },
  {path: '*', element: <NotFoundPage/>},
];