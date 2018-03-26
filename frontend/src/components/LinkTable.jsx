import React, { Component } from 'react';
import Paper from 'material-ui/Paper/Paper';
import Table, { TableBody, TableCell, TableHead, TableRow, TablePagination, TableFooter } from 'material-ui/Table';
import Typography from 'material-ui/Typography';
import _ from 'lodash';
import addEllipsis from 'add-ellipsis';
import moment from 'moment-timezone';
import Toolbar from 'material-ui/Toolbar/Toolbar';

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

  constructor(props) {
    super(props);
    this.state = {
      page: 0,
      rowsPerPage: 5
    };
    this.handleChangePage = this.handleChangePage.bind(this);
    this.handleChangeRowsPerPage = this.handleChangeRowsPerPage.bind(this);
  }

  mapTableData(data) {
    return _(data)
    .slice(this.state.page * this.state.rowsPerPage, this.state.page * this.state.rowsPerPage + this.state.rowsPerPage)
    .map((row) => (
      <TableRow key={row.mappedUrl}>
        <TableCell><a target='_blank' href={`${window.location.href}${row.mappedUrl}`}>{`${window.location.hostname}/${row.mappedUrl}`}</a></TableCell>
        <TableCell>{ moment.tz(row.timestamp, moment.tz.guess()).format('MMM D, YYYY') }</TableCell>
        <TableCell><a target='_blank' href={row.destUrl}>{row.destUrl.length > 27 ? addEllipsis(row.destUrl, 27) : row.destUrl}</a></TableCell>
        <TableCell>{row.clicks}</TableCell>
      </TableRow>
    )).compact().value();
  }

  handleChangePage(event, page) {
    this.setState({ page });
  }

  handleChangeRowsPerPage(event) {
    this.setState({ rowsPerPage: event.target.value });
  }

  render() {
    const styles = getStyles(this.state, this.props);
    return (
      <Paper>
        <Toolbar>
          <Typography variant="title">Shortened Links</Typography>
        </Toolbar>
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
        <TableFooter>
          <TableRow>
            <TablePagination
              colSpan={4}
              count={this.props.data.length}
              rowsPerPage={this.state.rowsPerPage}
              page={this.state.page}
              backIconButtonProps={{
                'aria-label': 'Previous Page'
              }}
              nextIconButtonProps={{
                'aria-label': 'Next Page'
              }}
              onChangePage={this.handleChangePage}
              onChangeRowsPerPage={this.handleChangeRowsPerPage}
            />
          </TableRow>
        </TableFooter>
      </Table>
    </Paper>
    );
  }
}
