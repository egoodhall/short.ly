import React, { Component } from 'react';
import Paper from 'material-ui/Paper/Paper';
import Table, { TableBody, TableCell, TableHead, TableRow } from 'material-ui/Table';
import _ from 'lodash';
import addEllipsis from 'add-ellipsis';
import moment from 'moment-timezone';

const getStyles = (state, props) => ({
  paper: {
    ...props.barStyle
  },
  content: {
    container: {
      display: 'flex',
      padding: '4px',
      justifyContent: 'right',
      alignItems: 'bottom'
    },
    input: {
      flexGrow: 1,
      marginRight: '8px',
      marginLeft: '8px'
    }
  }
});

export default class LinkTable extends Component {

  mapTableData(data) {
    return _.map(data, (row) => (
      <TableRow>
        <TableCell><a target='_blank' href={`${window.location.href}${row.mappedUrl}`}>{`${window.location.hostname}/${row.mappedUrl}`}</a></TableCell>
        <TableCell>{ moment.tz(row.timestamp, moment.tz.guess()).format('MMM D, YYYY') }</TableCell>
        <TableCell><a target='_blank' href={row.destUrl}>{row.destUrl.length > 27 ? addEllipsis(row.destUrl, 27) : row.destUrl}</a></TableCell>
        <TableCell>{row.clicks}</TableCell>
      </TableRow>
    ));
  }

  render() {
    const styles = getStyles(this.state, this.props);
    return (
      <Paper>
      <Table>
        <TableHead>
          <TableRow>
            <TableCell>Shortened Link</TableCell>
            <TableCell>Created On</TableCell>
            <TableCell>Original Link</TableCell>
            <TableCell>Total Clicks</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {this.mapTableData(this.props.data)}
        </TableBody>
      </Table>
    </Paper>
    );
  }
}
