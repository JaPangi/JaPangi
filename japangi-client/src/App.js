import './App.css';
import { Route, Routes } from 'react-router-dom';
import Main from './product/main/Main';
import SelectVendingMachine from './product/selectvendingmachine/SelectVendingMachine';
import VendingMachine from './product/vendingmachine/VendingMachine';

function App() {
  return (
    <Routes>
      <Route path='/' element={<Main/>} >
        <Route path='/vendingmachine/select' element={<SelectVendingMachine />} />
        <Route path='/vendingmachine/:id' element={<VendingMachine />} />
      </Route>
    </Routes>
  );
}

export default App;
