import './App.css';
import { useState } from 'react';
import LinkShortener from './components/LinkShortener';
import LinkResult from './components/LinkResult';

function App() {
  const [inputValue, setInputValue] = useState("");

  return (
    <div className="container">
      <LinkShortener setInputValue={setInputValue} />
      <LinkResult inputValue={inputValue} />
    </div>
  );
}

export default App;
