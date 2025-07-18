package org.gbif.dwc.terms;

import java.io.Serializable;
import java.net.URI;

public enum EcoTerm implements Term, AlternativeNames, Serializable {
    // TODO: add vocabulary tags?

    siteCount(EcoTerm.GROUP_SITE),
    siteNestingDescription(EcoTerm.GROUP_SITE),
    verbatimSiteDescriptions(EcoTerm.GROUP_SITE),
    verbatimSiteNames(EcoTerm.GROUP_SITE),
    geospatialScopeAreaValue(EcoTerm.GROUP_SITE),
    geospatialScopeAreaUnit(EcoTerm.GROUP_SITE),
    totalAreaSampledValue(EcoTerm.GROUP_SITE),
    totalAreaSampledUnit(EcoTerm.GROUP_SITE),
    reportedWeather(EcoTerm.GROUP_SITE),
    reportedExtremeConditions(EcoTerm.GROUP_SITE),
    targetHabitatScope(EcoTerm.GROUP_HABITAT_SCOPE),
    excludedHabitatScope(EcoTerm.GROUP_HABITAT_SCOPE),
    eventDurationValue(EcoTerm.GROUP_TEMPORAL_SCOPE),
    eventDurationUnit(EcoTerm.GROUP_TEMPORAL_SCOPE),
    targetTaxonomicScope(EcoTerm.GROUP_TAXONOMIC_SCOPE),
    excludedTaxonomicScope(EcoTerm.GROUP_TAXONOMIC_SCOPE),
    taxonCompletenessReported(EcoTerm.GROUP_TAXONOMIC_SCOPE),
    taxonCompletenessProtocols(EcoTerm.GROUP_TAXONOMIC_SCOPE),
    isTaxonomicScopeFullyReported(EcoTerm.GROUP_TAXONOMIC_SCOPE),
    isAbsenceReported(EcoTerm.GROUP_TAXONOMIC_SCOPE),
    absentTaxa(EcoTerm.GROUP_TAXONOMIC_SCOPE),
    hasNonTargetTaxa(EcoTerm.GROUP_TAXONOMIC_SCOPE),
    nonTargetTaxa(EcoTerm.GROUP_TAXONOMIC_SCOPE),
    areNonTargetTaxaFullyReported(EcoTerm.GROUP_TAXONOMIC_SCOPE),
    targetLifeStageScope(EcoTerm.GROUP_ORGANISMAL_SCOPE),
    excludedLifeStageScope(EcoTerm.GROUP_ORGANISMAL_SCOPE),
    isLifeStageScopeFullyReported(EcoTerm.GROUP_ORGANISMAL_SCOPE),
    targetDegreeOfEstablishmentScope(EcoTerm.GROUP_ORGANISMAL_SCOPE),
    excludedDegreeOfEstablishmentScope(EcoTerm.GROUP_ORGANISMAL_SCOPE),
    isDegreeOfEstablishmentScopeFullyReported(EcoTerm.GROUP_ORGANISMAL_SCOPE),
    targetGrowthFormScope(EcoTerm.GROUP_ORGANISMAL_SCOPE),
    excludedGrowthFormScope(EcoTerm.GROUP_ORGANISMAL_SCOPE),
    isGrowthFormScopeFullyReported(EcoTerm.GROUP_ORGANISMAL_SCOPE),
    hasNonTargetOrganisms(EcoTerm.GROUP_ORGANISMAL_SCOPE),
    verbatimTargetScope(EcoTerm.GROUP_ORGANISMAL_SCOPE),
    identifiedBy(EcoTerm.GROUP_IDENTIFICATION),
    identificationReferences(EcoTerm.GROUP_IDENTIFICATION),
    compilationTypes(EcoTerm.GROUP_METHODOLOGY_DESCRIPTION),
    compilationSourceTypes(EcoTerm.GROUP_METHODOLOGY_DESCRIPTION),
    inventoryTypes(EcoTerm.GROUP_METHODOLOGY_DESCRIPTION),
    protocolNames(EcoTerm.GROUP_METHODOLOGY_DESCRIPTION),
    protocolDescriptions(EcoTerm.GROUP_METHODOLOGY_DESCRIPTION),
    protocolReferences(EcoTerm.GROUP_METHODOLOGY_DESCRIPTION),
    isAbundanceReported(EcoTerm.GROUP_METHODOLOGY_DESCRIPTION),
    isAbundanceCapReported(EcoTerm.GROUP_METHODOLOGY_DESCRIPTION),
    abundanceCap(EcoTerm.GROUP_METHODOLOGY_DESCRIPTION),
    isVegetationCoverReported(EcoTerm.GROUP_METHODOLOGY_DESCRIPTION),
    isLeastSpecificTargetCategoryQuantityInclusive(EcoTerm.GROUP_METHODOLOGY_DESCRIPTION),
    hasVouchers(EcoTerm.GROUP_MATERIAL_COLLECTED),
    voucherInstitutions(EcoTerm.GROUP_MATERIAL_COLLECTED),
    hasMaterialSamples(EcoTerm.GROUP_MATERIAL_COLLECTED),
    materialSampleTypes(EcoTerm.GROUP_MATERIAL_COLLECTED),
    samplingPerformedBy(EcoTerm.GROUP_SAMPLING_EFFORT),
    isSamplingEffortReported(EcoTerm.GROUP_SAMPLING_EFFORT),
    samplingEffortProtocol(EcoTerm.GROUP_SAMPLING_EFFORT),
    samplingEffortValue(EcoTerm.GROUP_SAMPLING_EFFORT),
    samplingEffortUnit(EcoTerm.GROUP_SAMPLING_EFFORT);

    public static final String GROUP_SITE = "Site";
    public static final String GROUP_HABITAT_SCOPE = "Habitat Scope";
    public static final String GROUP_TEMPORAL_SCOPE = "Temporal Scope";
    public static final String GROUP_TAXONOMIC_SCOPE = "Taxonomic Scope";
    public static final String GROUP_ORGANISMAL_SCOPE = "Organismal Scope";
    public static final String GROUP_IDENTIFICATION = "Identification";
    public static final String GROUP_METHODOLOGY_DESCRIPTION = "Methodology Description";
    public static final String GROUP_MATERIAL_COLLECTED = "Material Collected";
    public static final String GROUP_SAMPLING_EFFORT = "Sampling Effort";


    private static final String PREFIX = "eco";
    private static final String NS = "http://rs.tdwg.org/eco/terms/";
    private static final URI NS_URI = URI.create(NS);

    private final String groupName;

    private EcoTerm(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String prefix() {
        return PREFIX;
    }

    @Override
    public URI namespace() {
        return NS_URI;
    }

    @Override
    public String simpleName() {
        return name();
    }

    @Override
    public boolean isClass() {
        return false;
    }

    @Override
    public String[] alternativeNames() {
        return new String[0];
    }
}
