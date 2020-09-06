import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'program',
        loadChildren: () => import('./program/program.module').then(m => m.GnsgappProgramModule),
      },
      {
        path: 'as-program',
        loadChildren: () => import('./as-program/as-program.module').then(m => m.GnsgappASProgramModule),
      },
      {
        path: 'expense',
        loadChildren: () => import('./expense/expense.module').then(m => m.GnsgappExpenseModule),
      },
      {
        path: 'revenue',
        loadChildren: () => import('./revenue/revenue.module').then(m => m.GnsgappRevenueModule),
      },
      {
        path: 'task',
        loadChildren: () => import('./task/task.module').then(m => m.GnsgappTaskModule),
      },
      {
        path: 'sevadar',
        loadChildren: () => import('./sevadar/sevadar.module').then(m => m.GnsgappSevadarModule),
      },
      {
        path: 'p-roul',
        loadChildren: () => import('./p-roul/p-roul.module').then(m => m.GnsgappPRoulModule),
      },
      {
        path: 'vendor',
        loadChildren: () => import('./vendor/vendor.module').then(m => m.GnsgappVendorModule),
      },
      {
        path: 'daily-program-report',
        loadChildren: () => import('./daily-program-report/daily-program-report.module').then(m => m.GnsgappDailyProgramReportModule),
      },
      {
        path: 'path-report',
        loadChildren: () => import('./path-report/path-report.module').then(m => m.GnsgappPathReportModule),
      },
      {
        path: 'expense-report',
        loadChildren: () => import('./expense-report/expense-report.module').then(m => m.GnsgappExpenseReportModule),
      },
      {
        path: 'revenue-report',
        loadChildren: () => import('./revenue-report/revenue-report.module').then(m => m.GnsgappRevenueReportModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class GnsgappEntityModule {}
