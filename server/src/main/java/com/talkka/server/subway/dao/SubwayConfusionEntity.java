package com.talkka.server.subway.dao;

import java.util.Objects;

import com.talkka.server.common.enums.TimeSlot;
import com.talkka.server.subway.enums.DayType;
import com.talkka.server.subway.enums.Line;
import com.talkka.server.subway.enums.Updown;
import com.talkka.server.subway.util.DayTypeConverter;
import com.talkka.server.subway.util.LineConverter;
import com.talkka.server.subway.util.UpdownConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "subway_confusion")
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SubwayConfusionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "subway_confusion_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "station_id")
	private SubwayStationEntity subwayStation;

	@Column(name = "station_code", nullable = false, length = 10)
	private String stationCode;

	@Column(name = "station_name", nullable = false, length = 50)
	private String stationName;

	@Column(name = "line_code", nullable = false, length = 4)
	@Convert(converter = LineConverter.class)
	private Line line;

	@Column(name = "day_type", nullable = false, length = 3)
	@Convert(converter = DayTypeConverter.class)
	private DayType dayType;

	@Column(name = "updown", nullable = false, length = 1)
	@Convert(converter = UpdownConverter.class)
	private Updown updown;

	@Column(name = "time_slot", nullable = false, length = 20)
	@Enumerated(EnumType.STRING)
	private TimeSlot timeSlot;

	@Column(name = "confusion")
	private Double confusion;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		SubwayConfusionEntity that = (SubwayConfusionEntity)o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}
}
