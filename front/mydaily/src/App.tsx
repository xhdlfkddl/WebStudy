import React from 'react';
import logo from './logo.svg';
import './App.css';
import Authentication from './views/AuthenticationView';
import BoardDetailView from './views/Board/BoardDetailView';

//# Router 설계 
//? 1. 'main' path 작성 : '/'
//? 2. 'auth' path 작성 : '/auth' (로그인 화면 / 회원가입 화면)
//? 3. 'myPage' path 작성 : '/myPage'
//? mypage 정보 수정??
//? 4. 'boardSearch' path 작성 : '/board/search/:content'
//? 5. 'boardDetail' path 작성 : '/board/detail/:boardNumber'
//? 6. 'boardWrite' path 작성 : '/board/write'
//? 7. 'boardUpdate' path 작성 : '/board/update/:boardNumber'

function App() {
  return (
    // <Authentication />    
    <BoardDetailView />
  );
}

export default App;
