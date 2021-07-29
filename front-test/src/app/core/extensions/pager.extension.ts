import { MatPaginatorIntl } from '@angular/material';
import { Injectable } from '@angular/core';

@Injectable()
export class PagerExtension extends MatPaginatorIntl {

    itemsPerPageLabel = 'Páginas:';
    nextPageLabel = 'Siguiente';
    previousPageLabel = 'Anterior';
    firstPageLabel = 'Principio';
    lastPageLabel = 'Ultimo';

    getRangeLabel = (page: number, pageSize: number, length: number) => {
        if (length === 0 || pageSize === 0) {
            return `0 de ${length}`;
        }
        length = Math.max(length, 0);
        const startIndex = page * pageSize;
        const endIndex = startIndex < length ? Math.min(startIndex + pageSize, length) : startIndex + pageSize;
        return `${startIndex + 1} - ${endIndex} de ${length}`;
    }
}
