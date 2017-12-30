import React, { Component } from 'react';
import { Input, Select } from 'antd';

const Option = Select.Option;

export default class UrlEntry extends Component {

  constructor(props) {
    super(props);
    this.state = {
      width: '50%',
    };
    this.updateDimensions();
  }

  componentDidMount() {
    this.updateDimensions();
    window.addEventListener('resize', () => this.updateDimensions());
  }

  updateDimensions() {
    if (window.innerWidth > 600) {
      this.setState({ width: '50%' });
    } else if (window.innerWidth > 350) {
      this.setState({ width: '80%' });
    } else {
      this.setState({ width: '94%' });
    }
  }

  updateSelect(value) {
    if (this.props.updateSelect) {
      this.props.updateSelect(value);
    }
  }

  updateUrl(e) {
    if (this.props.updateUrl) {
      this.props.updateUrl(e.target.value);
    }
  }

  render() {
    const select = (
      <Select
        defaultValue={this.props.prefix}
        onSelect={value => this.updateSelect(value)}
      >
        <Option value="http://">Http://</Option>
        <Option value="https://">Https://</Option>
      </Select>
    );
    return (
      <Input
        style={{ display: 'block', margin: 'auto', marginBottom: '30px', width: this.state.width }}
        size={'large'}
        value={this.props.value}
        addonBefore={select}
        placeholder="www.google.com/"
        onChange={url => this.updateUrl(url)}
      />
    );
  }
}
