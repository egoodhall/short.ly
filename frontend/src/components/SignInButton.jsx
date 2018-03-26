import React from 'react';
import { Button } from 'material-ui';
import SocialLogin from 'react-social-login';

const SignInButton = ({ children, triggerLogin, ...props }) => (
  <Button onClick={triggerLogin} {...props}>
    { children }
  </Button>
);

export default SocialLogin(SignInButton);
