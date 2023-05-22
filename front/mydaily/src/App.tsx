import './App.css';
import BoardDetailView from './views/Board/BoardDetailView';
import MainContent from './views/Main';
import { Route, Routes, useLocation } from 'react-router-dom';
import AuthenticationView from './views/AuthenticationView';
import axios, { AxiosResponse } from 'axios';
import { GET_USER, authorizationHeader } from './constants/api';
import ResponseDto from './apis/response';
import { GetUserResponseDto } from './apis/response/user';
import { useCookies } from "react-cookie";
import { useEffect } from "react";
import { useUserStore } from './stores';
import SearchTagListView from './views/Board/SearchTagListView';
import MyPageView from './views/MyPageView';

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

  const path = useLocation();
  const { setUser } = useUserStore();
  const [ cookies ] = useCookies();

  const getUser = (accessToken: string) => {
    axios.get(GET_USER, authorizationHeader(accessToken))
    .then((response) => getUserResponseHandler(response))
    .catch((error) => getUserErrorHandler(error))
  }

  const getUserResponseHandler = (response: AxiosResponse<any, any>) => {
    const { result, message, data } = response.data as ResponseDto<any>

    if (!result || !data) {
      alert(message);
      return;
    }
    const user = data as GetUserResponseDto;

    setUser(user);
  }

  const getUserErrorHandler = (error: any) => {
    console.log(error.message);
  }

  useEffect(() => {
    const accessToken = cookies.accessToken;
    if (accessToken) getUser(accessToken);

  },[path])

  return (
    <>
    <Routes>
      <Route path='/' element={(<MainContent />)} />
      <Route path='/auth' element={(<AuthenticationView />)} />
      <Route path='/mypage' element={(<MyPageView />)} />
      <Route path='/board/search-tag/:tag' element={(<SearchTagListView />)} />
      <Route path='/board/:boardNumber' element={(<BoardDetailView />)} />
    </Routes>
    </>
    
  );
}

export default App;
