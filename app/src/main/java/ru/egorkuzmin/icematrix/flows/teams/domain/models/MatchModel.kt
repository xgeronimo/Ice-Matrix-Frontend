package ru.egorkuzmin.icematrix.flows.teams.domain.models

import com.squareup.moshi.Json

data class MatchModel(
    @Json(name = "Team1")
    val firstTeam: TeamModel,
    @Json(name = "Team2")
    val secondTeam: TeamModel,
    @Json(name = "ScoreTeam1")
    val scoreTeam1: Int,
    @Json(name = "ScoreTeam2")
    val scoreTeam2: Int,
    @Json(name = "MajorPucksTeam1")
    val majorPucksTeam1: Int,
    @Json(name = "MajorPucksTeam2")
    val majorPucksTeam2: Int,
    @Json(name = "AdvantagesTeam1")
    val advantagesTeam1: Int,
    @Json(name = "AdvantagesTeam2")
    val advantagesTeam2: Int,
    @Json(name = "FaceoffsWonTeam1")
    val faceoffsWonTeam1: Int,
    @Json(name = "FaceoffsWonTeam2")
    val faceoffsWonTeam2: Int,
    @Json(name = "PenaltyMinutesTeam1")
    val penaltyMinutesTeam1: Int,
    @Json(name = "PenaltyMinutesTeam2")
    val penaltyMinutesTeam2: Int,
    @Json(name = "ShotsOnGoalTeam1")
    val shotsOnGoalTeam1: Int,
    @Json(name = "ShotsOnGoalTeam2")
    val shotsOnGoalTeam2: Int,
    @Json(name = "DistanceCoveredTeam1")
    val distanceCoveredTeam1: Double,
    @Json(name = "DistanceCoveredTeam2")
    val distanceCoveredTeam2: Double,
    @Json(name = "PuckPossessionTimeTeam1")
    val puckPossessionTimeTeam1: String,
    @Json(name = "PuckPossessionTimeTeam2")
    val puckPossessionTimeTeam2: String,
)