import React, { Component } from 'react';
import Input from './components/Input.jsx';
import LinkTable from './components/LinkTable.jsx';
import SignInButton from './components/SignInButton.jsx';

const getStyles = (state, props) => ({
  app: {
    container: {
      height: '100vh',
      fontFamily: '"Quicksand", sans-serif',
      textAlign: 'center',
      alignItems: 'center',
      display: 'flex',
      flexDirection: 'column',
      background: '#eee'
    },
    header: {
      width: '100%',
      height: '270px',
      textAlign: 'center',
      alignItems: 'center',
      display: 'flex',
      flexDirection: 'column',
      background: 'dodgerblue'
    },
    title: {
      color: 'white',
      fontSize: '5em',
      fontWeight: '100'
    }
  },
  inputBar: {
    marginLeft: '8px',
    marginRight: '8px',
    width: '700px'
  },
  table: {
    marginTop: '30px',
    width: '800px'
  }
});

const validURL = (str) => {
  var pattern = /^(?:(?:https?|ftp):\/\/)?(?:(?!(?:10|127)(?:\.\d{1,3}){3})(?!(?:169\.254|192\.168)(?:\.\d{1,3}){2})(?!172\.(?:1[6-9]|2\d|3[0-1])(?:\.\d{1,3}){2})(?:[1-9]\d?|1\d\d|2[01]\d|22[0-3])(?:\.(?:1?\d{1,2}|2[0-4]\d|25[0-5])){2}(?:\.(?:[1-9]\d?|1\d\d|2[0-4]\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)(?:\.(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)*(?:\.(?:[a-z\u00a1-\uffff]{2,})))(?::\d{2,5})?(?:\/\S*)?$/;
  if (!pattern.test(str)) {
    return false;
  } else {
    return true;
  }
};

class App extends Component {

  constructor(props) {
    super(props);
    const uid = localStorage.getItem('uid');
    this.state = {
      url: '',
      urlStats: [],
      uid: uid || undefined
    };

    this.onURLChanged = this.onURLChanged.bind(this);
    this.onShortenClicked = this.onShortenClicked.bind(this);
    this.retrieveLinks = this.retrieveLinks.bind(this);

    if (uid) {
      // this.retrieveLinks(uid);
    }
  }

  onURLChanged({ target: { value: url }}) {
    this.setState({ url });
  }

  onShortenClicked() {
    if (validURL(this.state.url)) {
      fetch(`${window.location.href}shorten`, {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          url: this.state.url,
          uid: this.state.uid || undefined
        })
      })
      .then(res => res.json())
      .then((json) => {
        if (!json.success) {
          console.log(json);
          return null;
        }
        if (this.state.uid) {
          this.setState({ url: '' });
          this.retrieveLinks(this.state.uid);
        }
        return json.data;
      });
    }
  }

  retrieveLinks(uid) {
    
    fetch(`${window.location.href}links/${uid}`)
    .then(res => res.json())
    .then((json) => {
      console.log(json);
      if (!json.success) {
        console.log(json);
      }
      return json.data;
    })
    .then((data) => {
      console.log(data);
      this.setState({ urlStats: data });
    });
  }

  handleSocialLogin(user) {
    this.setState({ uid: user.profile.id }, () => {
      this.retrieveLinks(this.state.uid);
      localStorage.setItem('uid', user.profile.id);
    });
  }

  handleSocialLoginFailure(err) {
    console.error(err);
  }

  render() {
    const styles = getStyles(this.state, this.props);
    return (
      <div style={styles.app.container}>
        <div style={styles.app.header}>
          <h1 style={styles.app.title}>Short.ly</h1>
          <Input value={this.state.url} onClick={this.onShortenClicked} onChange={this.onURLChanged} barStyle={styles.inputBar}/>
        </div>
        <div style={styles.table}>
          { this.state.uid && <LinkTable data={this.state.urlStats} /> }
          { !this.state.uid && <SignInButton
            style={{ marginTop: 8 }}
            variant='raised'
            provider='google'
            appId='142165402971-2jsroe947omcgessm520ksv4237omp03.apps.googleusercontent.com'
            onLoginSuccess={(user) => this.handleSocialLogin(user)}
            onLoginFailure={(err) => this.handleSocialLoginFailure(err)}
          >Sign in With Google</SignInButton> }
        </div>
      </div>
    );
  }
}

export default App;
