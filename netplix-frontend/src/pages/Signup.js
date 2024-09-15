import React, { useState } from 'react';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';
import {useNavigate} from "react-router-dom";

function Signup() {
    const [username, setUsername] = useState('danny.kim');
    const [email, setEmail] = useState('danny.kim@kakaobank.com');
    const [password1, setPassword1] = useState('1234');
    const [password2, setPassword2] = useState('1234');
    const [phone, setPhone] = useState('010-1234-5678');

    const navigate = useNavigate();  // 페이지 이동을 위한 useNavigate 훅 사용

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            // /login API에 POST 요청
            const response = await axios.post('http://localhost:8080/api/v1/user/register', {
                username,
                password: password1,
                email,
                phone
            });

            // 로그인 성공 시 대시보드로 이동
            if (response.status === 200) {
                // 토큰 또는 로그인 성공 메시지 처리
                localStorage.setItem('token', response.data.token);
                navigate('/dashboard');  // 대시보드로 이동
            }
        } catch (error) {
            // 오류 처리
            console.error('register failed:', error);
            alert('회원가입 실패');
        }
    };

    return (
        <div className="container d-flex justify-content-center align-items-center vh-100">
            <div className="card shadow-sm p-4" style={{ width: '100%', maxWidth: '400px' }}>
                <h3 className="text-center mb-4">회원가입</h3>
                <form onSubmit={handleSubmit}>
                    <div className="mb-3">
                        <label htmlFor="email" className="form-label">이메일</label>
                        <input
                            type="email"
                            className="form-control"
                            id="email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            required
                        />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="password1" className="form-label">비밀번호</label>
                        <input
                            type="password"
                            className="form-control"
                            id="password1"
                            value={password1}
                            onChange={(e) => setPassword1(e.target.value)}
                            required
                        />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="password2" className="form-label">비밀번호 확인</label>
                        <input
                            type="password"
                            className="form-control"
                            id="password2"
                            value={password2}
                            onChange={(e) => setPassword2(e.target.value)}
                            required
                        />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="username" className="form-label">사용자 명</label>
                        <input
                            type="text"
                            className="form-control"
                            id="username"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                            required
                        />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="phone" className="form-label">전화번호</label>
                        <input
                            type="text"
                            className="form-control"
                            id="phone"
                            value={phone}
                            onChange={(e) => setPhone(e.target.value)}
                            required
                        />
                    </div>
                    <button type="submit" className="btn btn-primary w-100">
                        회원가입
                    </button>
                </form>
            </div>
        </div>
    );
}

export default Signup;