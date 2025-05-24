import {createBrowserRouter, RouterProvider} from 'react-router-dom';
import {routes} from './routers';

export const AppRouter = () => {
  return <RouterProvider router={createBrowserRouter(routes)}/>;
};
