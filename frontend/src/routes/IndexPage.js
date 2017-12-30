import React, { Component } from 'react';
import 'antd/dist/antd.less';
import { connect } from 'dva';
import { Button } from 'antd';
import './IndexPage.css';
import UrlEntry from '../components/UrlEntry';

const styles = {
  app: {
    width: '100%',
    // height: '50%',
    margin: 'auto',
    backgroundColor: '#0058B2',
    position: 'absolute',
    top: 0,
    bottom: 0,
    left: 0,
    right: 0,
    display: 'flex',
    flexDirection: 'column',
  },
  items: {
    width: '100%',
    margin: 'auto',
    justifyContent: 'center',
    alignItems: 'center',
  },
  button: {
    margin: 'auto',
    textAlign: 'center',
    display: 'block',
    marginBottom: '20%',
  },
  title: {
    fontFamily: 'Nunito',
    fontSize: '6em',
    textAlign: 'center',
    marginBottom: 0,
  },
  subtitle: { fontFamily: 'Nunito', textAlign: 'center',
  },
};

class IndexPage extends Component {

  constructor(props) {
    super(props);
    this.state = {
      prefix: 'https://',
      url: '',
      urlValid: false,
      titleSize: '6em',
      subtitleSize: '3em',
    };
  }

  componentDidMount() {
    this.updateDimensions();
    window.addEventListener('resize', () => this.updateDimensions());
  }

  validUrl(url) {
    const regex = /^(?:http(s)?:\/\/)?[\w.-]+(?:\.[\w\.-]+)+[\w\-\._~:/?#[\]@!\$&'\(\)\*\+,;=.]+$/gm;
    return (url.match(regex) || []).length !== 0;
  }

  updateUrl(url) {
    this.setState({ url, urlValid: this.validUrl(this.state.prefix + url) });
  }

  updatePrefix(prefix) {
    this.setState({ prefix });
  }

  updateDimensions() {
    if (window.innerWidth > 600) {
      this.setState({
        titleSize: '6em',
        subtitleSize: '3em',
      });
    } else if (window.innerWidth > 350) {
      this.setState({
        titleSize: '4em',
        subtitleSize: '2em',
      });
    } else {
      this.setState({
        titleSize: '2.5em',
        subtitleSize: '1.5em',
      });
    }
  }

  render() {
    return (
      <div style={styles.app}>
        <div style={styles.items}>
          <h1 style={{ color: 'white', fontFamily: 'Nunito', textAlign: 'center', fontSize: this.state.titleSize, marginBottom: 0 }}>Short.ly</h1>
          <h2 style={{ color: 'white', fontFamily: 'Nunito', textAlign: 'center', fontSize: this.state.subtitleSize }}>A readable URL shortener</h2>
          <UrlEntry
            updateUrl={url => this.updateUrl(url)}
            updatePrefix={prefix => this.updatePrefix(prefix)}
            prefix={this.state.prefix}
            value={this.state.url}
          />
          <Button
            style={styles.button}
            type="primary"
            disabled={!this.state.urlValid}
            size="large"
          >
              Shorten!
          </Button>
        </div>
      </div>
    );
  }
}

export default connect()(IndexPage);
