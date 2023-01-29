import http from '../http-common'
import authHeader from './userServices/auth-header';

class MasterService{
    getCinemaSales() {
        return http.get('/admin/sales/cinema', { headers: authHeader() });
      }
      getCountUser() {
        return http.get('/admin/countUser', { headers: authHeader() });
      }
      getCountMemTk() {
        return http.get('/admin/memberTicketCount', { headers: authHeader() });
      }
      getCountNonMemTk() {
        return http.get('/admin/nonmemberTicketCount', { headers: authHeader() });
      }
     getMonthVistor() {
        return http.get('/admin/vistor/month', { headers: authHeader() });
      }
    getMoiveSale(){
      return http.get('/admin/sales/movie', { headers: authHeader() }); 
    }
}

export default new MasterService();