import './App.css';
import { Route, Routes } from 'react-router-dom';
import Main from './components/main/Main';
import SelectVendingMachine from './components/product/selectvendingmachine/SelectVendingMachine';
import VendingMachine from './components/product/vendingmachine/VendingMachine';
import AdminLogin from './components/admin/AdminLogin';
import AdminSelectVendingMachine from './components/admin/AdminSelectVendingMachine';
import AdminStatistic from './components/admin/AdminStatistic';
import DrinkPurchase from './components/product/vendingmachine/DrinkPurchase';
import Changes from './components/product/vendingmachine/Change';
import AdminPasswordChange from './components/admin/AdminPasswordChange';

function App() {
  return (
    <Routes>
      <Route path='/' element={<Main/>} >
        <Route path='/vendingmachine/select' element={<SelectVendingMachine />} />
        <Route path='/vendingmachine/:vendingmachineId' element={<VendingMachine />} />
        <Route path='/vendingmachine/:vendingmachineId/drink/:drinkId/purchase' element={<DrinkPurchase />} />
        <Route path='/vendingmachine/:vendingmachineId/change' element={<Changes />} />

        <Route path='/admin/login' element={<AdminLogin />} />
        <Route path='/admin/password/change/:username' element={<AdminPasswordChange />} />
        <Route path='/admin/vendingmachine/select' element={<AdminSelectVendingMachine />} />
        <Route path='/admin/vendingmachine/:vendingmachineId' element={<AdminStatistic />} />
      </Route>
    </Routes>
  );
}

export default App;
