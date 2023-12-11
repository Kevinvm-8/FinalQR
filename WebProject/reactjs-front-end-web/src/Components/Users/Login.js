import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import {
  MDBBtn,
  MDBContainer,
  MDBRow,
  MDBCol,
  MDBIcon,
  MDBInput,
  MDBCardBody,
  MDBCardImage,
  MDBCard
} from 'mdb-react-ui-kit';

function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleSignIn = async () => {
    try {
      const requestData = {
        username: username,
        password: password,
      };

      const response = await fetch('/api/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json',
        },
        credentials: 'include',
        body: JSON.stringify(requestData),
      });

      if (response.ok) {
        const responseData = await response.json();
        console.log('Login successful');

        localStorage.setItem('userToken', responseData.token);

        navigate('/home', { replace: true });
      } else {
        console.error('Login failed');
        setError('Login failed. Please check your username and password.');
      }
    } catch (error) {
      console.error('Error connecting to the API:', error);
      setError('Error connecting to the API. Please try again later.');
    }
  };

  useEffect(() => {
    document.body.style.backgroundColor = '#F8F3ED';
    return () => {
      document.body.style.backgroundColor = '';
    };
  }, []);

  return (
    <MDBContainer className="my-5">
      <MDBCard>
        <MDBRow className='g-0'>
          <MDBCol md='6'>
            {/* Set maxHeight to 100vh for the image */}
            <MDBCardImage
              src='https://mdbootstrap.com/img/new/ecommerce/vertical/004.jpg'  // Đường dẫn đến hình ảnh của bạn
              alt="login form"
              className='rounded-start w-100'
              style={{ maxHeight: '85vh', objectFit: 'cover' }}
            />
          </MDBCol>

          <MDBCol md='6'>
            <MDBCardBody className='d-flex flex-column'>
              <div className='d-flex flex-row mt-5'>
                <MDBIcon fas icon="cubes fa-3x me-3" style={{ color: '#ff6219' }} />
                <span className="h1 fw-bold mb-5 text-center">Sign in</span>
              </div>



              <MDBInput wrapperClass='mb-4' placeholder='Username' id='formControlLg' type='text' size="lg" value={username}
                onChange={(e) => setUsername(e.target.value)} />
              <MDBInput wrapperClass='mb-4' placeholder='Password' id='formControlLg' type='password' size="lg" value={password}
                onChange={(e) => setPassword(e.target.value)} />
              {error && (
                <div className="alert alert-danger" role="alert">
                  {error}
                </div>
              )}
              <MDBBtn className="mb-4 px-5" color='info' size='lg' onClick={handleSignIn} >Login</MDBBtn>

              <div className='d-flex flex-row justify-content-center mt-4'>
                <a href="#!" className="small text-muted me-1">Terms of use.</a>
                <a href="#!" className="small text-muted">Privacy policy</a>
              </div>
            </MDBCardBody>
          </MDBCol>
        </MDBRow>
      </MDBCard>
    </MDBContainer>
  );
}

export default Login;
