import React from 'react';
import { Navigate } from 'react-router-dom';

function ProtectedRoute({ children }) {
    const token = localStorage.getItem('token');  // 로컬 스토리지에서 토큰 확인

    // 토큰이 없으면 로그인 페이지로 리디렉션
    if (!token) {
        return <Navigate to="/login" />;
    }

    // 토큰이 있으면 자식 컴포넌트를 렌더링
    return children;
}

export default ProtectedRoute;