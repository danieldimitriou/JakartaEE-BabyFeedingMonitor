import {Time} from "@angular/common";

export interface FeedingSession {
  id?: number;
  amountConsumed: number;
  date: Date; // LocalDateTime format as a string
  startTime: string;
  endTime: string;
  duration?: number;
}
