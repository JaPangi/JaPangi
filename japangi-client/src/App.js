import './App.css';
import { Route, Routes } from 'react-router-dom';
import Main from './components/main/Main';
import SelectVendingMachine from './components/product/selectvendingmachine/SelectVendingMachine';
import VendingMachine from './components/product/vendingmachine/VendingMachine';
import AdminLogin from './components/admin/AdminLogin';
import AdminSelectVendingMachine from './components/admin/AdminSelectVendingMachine';
import DrinkPurchase from './components/product/vendingmachine/DrinkPurchase';
import Changes from './components/product/vendingmachine/Change';
import AdminPasswordChange from './components/admin/AdminPasswordChange';
import AdminMain from './components/admin/AdminMain';
import ManageDrink from './components/admin/manage/ManageDrink';
import ManageChange from './components/admin/manage/ManageChange';
import ManageStatistic from './components/admin/manage/ManageStatictic';
import ManageDrinkDetail from './components/admin/manage/ManageDrinkDetail';
import Redirect from './components/main/Redirect';

function App() {
  return (
    <Routes>
      <Route path='/' element={<Main/>} >
        <Route path='/' element={<Redirect />} />
        <Route path='/vendingmachine/select' element={<SelectVendingMachine />} />
        <Route path='/vendingmachine/:vendingmachineId' element={<VendingMachine />} />
        <Route path='/vendingmachine/:vendingmachineId/drink/:drinkId/purchase' element={<DrinkPurchase />} />
        <Route path='/vendingmachine/:vendingmachineId/change' element={<Changes />} />

        <Route path='/admin/login' element={<AdminLogin />} />
        <Route path='/admin/password/change/:username' element={<AdminPasswordChange />} />
        <Route path='/admin/vendingmachine/select' element={<AdminSelectVendingMachine />} />
        <Route path='/admin/vendingmachine/:vendingmachineId' element={<AdminMain />} />

        <Route path='/admin/vendingmachine/:vendingmachineId/manage/drink' element={<ManageDrink />} />
        <Route path='/admin/vendingmachine/:vendingmachineId/manage/drink/:drinkId' element={<ManageDrinkDetail />} />
        <Route path='/admin/vendingmachine/:vendingmachineId/manage/change' element={<ManageChange />} />
        <Route path='/admin/vendingmachine/:vendingmachineId/manage/statistic' element={<ManageStatistic />} />
      </Route>
    </Routes>
  );
}

export default App;
