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
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class GnsgappEntityModule {}
