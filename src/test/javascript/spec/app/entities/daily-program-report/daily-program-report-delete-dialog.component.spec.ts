import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GnsgappTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { DailyProgramReportDeleteDialogComponent } from 'app/entities/daily-program-report/daily-program-report-delete-dialog.component';
import { DailyProgramReportService } from 'app/entities/daily-program-report/daily-program-report.service';

describe('Component Tests', () => {
  describe('DailyProgramReport Management Delete Component', () => {
    let comp: DailyProgramReportDeleteDialogComponent;
    let fixture: ComponentFixture<DailyProgramReportDeleteDialogComponent>;
    let service: DailyProgramReportService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GnsgappTestModule],
        declarations: [DailyProgramReportDeleteDialogComponent],
      })
        .overrideTemplate(DailyProgramReportDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DailyProgramReportDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DailyProgramReportService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
