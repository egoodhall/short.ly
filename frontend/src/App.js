import React, { Component } from 'react';
import Input from './components/Input.jsx';
import LinkTable from './components/LinkTable.jsx';

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

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      url: '',
      urlStats: [
        { shortened: 'http://localhost:8080/3QE5bn', original: 'https://www.youtube.com/watch?v=0CFuCYNx-1g', creationDate: '3/25/2018', clicks: 1 },
        { shortened: 'http://localhost:8080/4Tzky5', original: 'https://github.com/emm035', creationDate: '3/11/2018', clicks: 5 },
        { shortened: 'http://localhost:8080/Sz71Wq', original: 'https://www.linkedin.com/in/eric-marshall', creationDate: '2/18/2018', clicks: 17 }
      ]
    };

    this.onURLChanged = this.onURLChanged.bind(this);
    this.onShortenClicked = this.onShortenClicked.bind(this);
  }

  onURLChanged({ target: { value: url }}) {
    this.setState({ url });
  }

  onShortenClicked() {
    fetch('http://localhost:8080/shorten', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        url: this.state.url
      })
    })
    .then(res => res.json())
    .then(json => console.log(json));
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
          <LinkTable data={this.state.urlStats} />
        </div>
      </div>
    );
  }
}

export default App;
